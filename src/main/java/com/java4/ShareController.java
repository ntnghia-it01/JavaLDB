package com.java4;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/share")
public class ShareController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Nhận 2 dữ liệu từ detail gửi qua
		String videoID = req.getParameter("video_id");
		String email = req.getParameter("email");
		try {
			// Thông số kết nối Smtp Server (Gmail STARTTLS - cổng 587)
			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.starttls.required", "true");
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.setProperty("mail.smtp.ssl.checkserveridentity", "true");

			// Kết nối Smtp Server
			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					String username = "ntnghia.it01@gmail.com";
					String password = "duwb jloy hoqm vykr"; // App Password
					return new PasswordAuthentication(username, password);
				}
			});

			session.setDebug(true);

			// Tạo message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ntnghia.it01@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, email);
			message.setSubject("Chia sẽ video", "utf-8");
			message.setText("http://localhost:8080/JavaLDB/video-detail?video_id=" + videoID, "utf-8", "html");
			message.setReplyTo(message.getFrom());
			// Gửi message
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/video-detail?video_id=" + videoID);
	}
}
