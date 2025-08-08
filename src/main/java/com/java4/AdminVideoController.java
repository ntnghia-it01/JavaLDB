package com.java4;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.VideoDAO;
import com.java4.entities.Video;

@WebServlet("/admin/videos")
public class AdminVideoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Video> videos = VideoDAO.findAll();

		req.setAttribute("videos", videos);

		req.getRequestDispatcher("/admin-videos.jsp").forward(req, resp);
	}
}
