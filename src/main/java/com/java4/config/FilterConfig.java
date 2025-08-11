package com.java4.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({ "/admin/*", "/user/*" })
public class FilterConfig implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Filter destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
//		Lấy userId và role từ cookie
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String userId = null;
		String role = null;
		Cookie cookies[] = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user_id")) {
				userId = cookie.getValue();
			}
			if (cookie.getName().equals("role")) {
				role = cookie.getValue();
			}
		}
		String path = req.getRequestURI();
//		Những trang này cần đăng nhập 
		if (userId == null || role == null) {
//			Người dùng chưa đăng nhập 
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
//		Đã có đủ userId và role 
		if (path.contains("/admin/") && role.equals("user")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		if (path.contains("/user/") && role.equals("admin")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
//		if (path.contains("/admin/") || path.contains("/user/")) {
//			
//		}
//		Có hàm này thì controller quản lý path mới được kích hoạt
		chain.doFilter(request, response);
	}

	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		System.out.println("Filter init");
	}

}

// User = mở url hoặc thực hiện chức năng ở UI => WebFilter => Controller 
// => Model => WebFilter => View (User xem) = click => WebFilter