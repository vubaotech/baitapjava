package vn.buoi2.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		 PrintWriter printWriter = resp.getWriter();
		 String name="";
		 Cookie[] cookie = req.getCookies();
		 for (Cookie c: cookie) {
		 if(c.getName().equals("username")) {
		 name = c.getValue();}}
		 if(name.equals("")){
		 resp.sendRedirect("/buoi2/login");
		 }
		 printWriter.println("Xin chao " + name);
	}
}
