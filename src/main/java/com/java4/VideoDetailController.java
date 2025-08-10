package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.FavoriteDAO;
import com.java4.dao.VideoDAO;
import com.java4.entities.Favorite;
import com.java4.entities.Video;

@WebServlet("/video-detail")
public class VideoDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("video_id");

//		nếu không có id thì chuyển về home 
		if (id == null) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}

		Video video = VideoDAO.findById(id); // id không tồn tại video == null

		if (video == null) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}

		req.setAttribute("video", video);

//		tăng lượt xem khi vào chi tiết 
		video.setViews(video.getViews() + 1);
		VideoDAO.update(video);

		Cookie cookies[] = req.getCookies();
		String userId = null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user_id")) {
				userId = cookie.getValue();
				break;
			}
		}

		if (userId != null) {
			Favorite favorite = FavoriteDAO.checkFavoriteVideo(Integer.parseInt(userId), Integer.parseInt(id));

			req.setAttribute("favorite", favorite);
		}

		req.getRequestDispatcher("/video-detail.jsp").forward(req, resp);
	}
}
