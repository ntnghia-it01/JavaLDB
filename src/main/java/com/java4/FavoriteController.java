package com.java4;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.FavoriteDAO;
import com.java4.dao.UserDAO;
import com.java4.dao.VideoDAO;
import com.java4.entities.Favorite;
import com.java4.entities.User;
import com.java4.entities.Video;

@WebServlet("/user/favorites")
public class FavoriteController extends HttpServlet {

//	danh sách yêuh thích
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

//	Xử lý yêu thích
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String video_id = req.getParameter("video_id");

		Cookie cookies[] = req.getCookies();
		String userId = null;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user_id")) {
				userId = cookie.getValue();
				break;
			}
		}

		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		Favorite favorite = FavoriteDAO.checkFavoriteVideo(Integer.parseInt(userId), Integer.parseInt(video_id));

		if (favorite != null) {
			System.out.println(favorite.getId());
			FavoriteDAO.delete(favorite);
		} else {
			Favorite favoriteSave = new Favorite();
			favoriteSave.setLikeDate(new Date());
			Video video = VideoDAO.findById(video_id);
			favoriteSave.setVideo(video);
			User user = UserDAO.findById(userId);
			favoriteSave.setUser(user);

			FavoriteDAO.insert(favoriteSave);
		}

		resp.sendRedirect(req.getContextPath() + "/video-detail?video_id=" + video_id);
	}
}
