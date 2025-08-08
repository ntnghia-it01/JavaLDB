package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.VideoDAO;
import com.java4.entities.Video;

@WebServlet("/admin/video-form")
public class AdminVideoFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/admin-video-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//		lấy dữ liệu từ input
		String title = req.getParameter("title");
		String urlImage = req.getParameter("urlImage");
		String urlVideo = req.getParameter("urlVideo");
		String desc = req.getParameter("desc");
		String status = req.getParameter("status");

//		Gửi dữ liệu ngược lại qua form 
		req.setAttribute("title", title);
		req.setAttribute("urlImage", urlImage);
		req.setAttribute("urlVideo", urlVideo);
		req.setAttribute("desc", desc);
		req.setAttribute("status", status);

//		validate 
//		- Tiêu đề không rỗng 
//		- url ảnh và video đúng định dạng 
//		- mô tả không rỗng và phải có từ 10 - 500 ký tự 

		boolean hasError = false;

		if (title.isBlank()) {
			req.setAttribute("errTitle", "Tiêu đề không được bỏ trống");
			hasError = true;
		}

		if (urlImage.matches(
				"[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
			req.setAttribute("errImage", "Url ảnh sai định dạng");
			hasError = true;
		}

		if (urlVideo.matches(
				"[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
			req.setAttribute("errVideo", "Url video sai định dạng");
			hasError = true;
		}

		if (desc.trim().length() < 10 || desc.trim().length() > 500) {
			req.setAttribute("errDesc", "Mô tả phải có từ 10 - 500 ký tự");
			hasError = true;
		}

		if (!hasError) {
//			Lưu video 

			Video video = new Video();
			video.setTitle(title);
			video.setPoster(urlImage);
			video.setUrl(urlVideo);
			video.setDesc(desc);
			video.setActive(status.equals("1"));
			video.setViews(0);

			VideoDAO.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}

		req.getRequestDispatcher("/admin-video-form.jsp").forward(req, resp);
	}
}
