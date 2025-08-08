package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.UserDAO;
import com.java4.entities.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		Phương thức lấy dữ liệu từ form jsp gửi qua 

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullName = req.getParameter("fullName");

//		Phương thức để gửi dữ liệu qua jsp 

		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.setAttribute("fullName", fullName);

//		Kiểm tra lỗi form trước khi tương tác với DB
//		Đúng định dang email
//		Password lớn hơn 6 ký tự
//		Tên không bỏ trống 

//		Viết lệnh kiểm tra các lỗi như trên
//		Nếu có lỗi thực hiện gửi attribute với key errEmail, errPassword,..
//		Và hiện thị lỗi ở jsp phía dưới từng ô input tương ứng 

		boolean hasError = false;

		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			req.setAttribute("errEmail", "Email sai định dạng");
			hasError = true;
		}

		if (password.trim().length() < 6) {
			req.setAttribute("errPassword", "Mật khẩu phải có ít nhất 6 ký tự");
			hasError = true;
		}

		if (fullName.isBlank()) {
			req.setAttribute("errFullName", "Họ và tên không được bỏ trống");
			hasError = true;
		}

//		Code chạy đến đây có phát sinh lỗi của các input không?
//		Nếu không có mới thực hiện tiếp công việc liên quan đến db 

		if (!hasError) {
//			Xử lý db 

			boolean checkEmailExist = UserDAO.checkEmailExist(email);

			if (checkEmailExist) {
				req.setAttribute("errEmail", "Email đã tồn tại");
			} else {
				User user = new User();
//				user.setId(0)
				user.setEmail(email);
				user.setPassword(password);
				user.setFullName(fullName);
				user.setAdmin(false);
				UserDAO.insert(user);

				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		}

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}

// User => gõ url /register => Controller regsiter do Get
// => Khi user ấn submit form => Dữ liệu của form sẽ được gửi ngược lại C
// => Thông qua do Post để xử lý thông tin 