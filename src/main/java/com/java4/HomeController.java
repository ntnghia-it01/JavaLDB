package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.config.DBConnection;

import javax.persistence.EntityManager;

// Tên path trong Web servlet phải là duy nhất trong cả dự án
// Tên path bắt buộc phải bắt đầu bằng ký tự "/...."
// 1 Controller chỉ quản lý duy nhất 1 path servlet 
@WebServlet("/") // http://localhost:8080/JavaLDB/
//@WebServlet("/home") // http://localhost:8080/JavaLDB/home
//@WebServlet("/home/product") // http://localhost:8080/JavaLDB/home/product
public class HomeController extends HttpServlet {
	// Chỉ dùng để viết code xử lý
	// Validate form
	// Xử lý database (CRUD)

	// Mô hình MVC WEB Servlet
	// M => Model => request.attribute
	// V => View => File *.jsp
	// C => Controller => File *.java kế thừa HttpServlet

//	User => C => M => V => User 

//	Trong Servlet khi người dùng gõ url tưng ứng thì phương thức doGet sẽ được chạy 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Model => key, value
//		Key tương ứng với tên biến, phải đặt khác nhau trong phạm vi 1 phương thức
//		value giá trị của biến mà muốn truyền qua => jsp 
		req.setAttribute("name", "Nguyen Van A");

		req.setAttribute("name", 1);
//		=> name == Nguyen Van A
//		=> Name == Nguyen Van B

//		dòng này req.getRequestDispatcher 
//		Lúc nào cũng phải đặt cuối phương thức 

		EntityManager entityManager = DBConnection.getEntityManager();
		System.out.println(entityManager.getProperties().toString());

		req.getRequestDispatcher("/home-page.jsp").forward(req, resp);
	}

//	Chỉ dùng để xử lý form 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		dòng này req.getRequestDispatcher 
//		Lúc nào cũng phải đặt cuối phương thức 
		req.getRequestDispatcher("/home-page.jsp").forward(req, resp);
	}
}

// Khi có giao diện form Đăng nhập 
// Trong Servlet sẽ có 2 phương thức doGet và doPost 
// doGet dùng để hiển thị giao diện khi user gọi url 
// doPost dùng để nhận dữ liệu từ form và kiểm tra logic để trả về thông tin 

// Khi *jsp được quản lý bởi 1 Servlet không có form 
// (không có input không có submit không xuất hiện thẻ <form>)
// Servlet quản lý nó chỉ cần có doGet 

// Khi *jsp được quản lý bởi 1 Servlet có form (có ít nhất 1 thẻ <form>) 
// Servlet quản lý nó bắt buộc phải có doGet và doPost
