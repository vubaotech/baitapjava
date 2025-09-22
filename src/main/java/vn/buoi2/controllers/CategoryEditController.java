package vn.buoi2.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale.Category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.buoi2.services.CategoryService;
import vn.buoi2.services.impl.CategoryServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50)

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Category category = cateService.get(Integer.parseInt(id));
		req.setAttribute("category", category);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SecurityException {
//		try {
//			String name = req.getParameter("name");
//			Part part = req.getPart("image");
//			String realPath = req.getServletContext().getRealPath("/uploads");
//			String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//			if (!Files.exists(Paths.get(realPath))) {
//				Files.createDirectory(Paths.get(realPath));
//			}
//			part.write(realPath + "/" + filename);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if (url.contains("/admin/category/update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            Category category = new Category();
            category.setCategoryid(categoryid);
            category.setCategoryname(categoryname);
            category.setStatus(status);
            Category cateOld = cateService.findById(categoryid);
            String fileold = cateOld.getImages();
            String images = "";
            String uploadPath = UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("icon");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // upload file
                    part.write(uploadPath + "/" + fname);
                    // ghi tên file vào data
                    category.setImages(fname);
                } else {
                    category.setImages("hinh.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }

	}

}
