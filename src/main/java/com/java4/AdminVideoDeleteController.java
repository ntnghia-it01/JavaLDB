package com.java4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java4.dao.VideoDAO;

@WebServlet("/admin/video-delete")
public class AdminVideoDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		System.out.println(id);

		VideoDAO.delete(VideoDAO.findById(id));

		resp.sendRedirect(req.getContextPath() + "/admin/videos");
	}
}
