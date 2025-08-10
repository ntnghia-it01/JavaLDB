package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.UserDAO;
import com.java4.entities.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		Lấy dữ liệu được user nhập từ form
		String email = req.getParameter("email");
		String password = req.getParameter("password");

//		Gửi dữ liệu lấy được qua form của user 
		req.setAttribute("email", email);
		req.setAttribute("password", password);

//		Kiểm tra lỗi
//		Email không đúng định dạng
//		Password không bỏ trống 

		boolean hasError = false;

		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			req.setAttribute("errEmail", "Email sai định dạng");
			hasError = true;
		}

		if (password.length() == 0) {
			req.setAttribute("errPassword", "Mật khẩu không được rỗng");
			hasError = true;
		}

		if (!hasError) {
			User user = UserDAO.login(email, password);

			if (user != null) {
//				Lưu user id và role vào cookie để quản lý thông 
				Cookie cookieUserId = new Cookie("user_id", String.valueOf(user.getId()));
				Cookie cookieRole = new Cookie("role", user.isAdmin() ? "admin" : "user");

				resp.addCookie(cookieUserId);
				resp.addCookie(cookieRole);

//				Kiểm tra vài trò để chuyển trang 
				if (user.isAdmin()) {
					resp.sendRedirect(req.getContextPath() + "/admin/videos");
				} else {
					resp.sendRedirect(req.getContextPath() + "/");
				}

				return;
			}

			req.setAttribute("errEmail", "Email hoặc mật khẩu không đúng");
		}

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
