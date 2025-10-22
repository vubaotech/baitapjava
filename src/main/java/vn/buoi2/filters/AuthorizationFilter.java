package vn.buoi2.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.buoi2.models.UserModel;

public class AuthorizationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();

		HttpSession session = req.getSession(false);
		UserModel user = null;
		if (session != null) {
			user = (UserModel) session.getAttribute("account"); //
		}

		// Admin
		if (uri.startsWith(contextPath + "/admin")) {
			if (user == null) {
				resp.sendRedirect(contextPath + "/login");
			} else if (user.getRoleid() == 1) { //
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(contextPath + "/home?error=unauthorized");
			}
		}

		// User
		else if (uri.startsWith(contextPath + "/home") || uri.startsWith(contextPath + "/hello")
				|| uri.startsWith(contextPath + "/member")) { //

			if (user == null) {
				resp.sendRedirect(contextPath + "/login");
			} else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}
}
