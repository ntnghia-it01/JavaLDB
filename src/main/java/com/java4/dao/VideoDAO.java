package com.java4.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.java4.config.DBConnection;
import com.java4.entities.Video;

public class VideoDAO {

	public static boolean insert(Video video) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.persist(video);

			manager.getTransaction().commit();

			manager.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			manager.close();
			return false;
		}
	}

	public static boolean update(Video video) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.merge(video);

			manager.getTransaction().commit();

			manager.close();
			return true;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			manager.close();
			return false;
		}
	}

	public static boolean delete(Video video) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.remove(video);

			manager.getTransaction().commit();

			manager.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			manager.close();
			return false;
		}
	}

	public static Video findById(String id) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			Video video = manager.find(Video.class, Integer.parseInt(id));

			manager.close();
			return video;
		} catch (Exception e) {
			e.printStackTrace();
			manager.close();
			return null;
		}
	}

//	Viết thêm 1 hàm lấy tất cả video đang có ở database 

	public static List<Video> findAll() {
		List<Video> videos = new ArrayList<Video>();
		EntityManager manager = DBConnection.getEntityManager();
		try {

			String sql = "SELECT * FROM videos";

			Query query = manager.createNativeQuery(sql, Video.class);

			videos = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			manager.close();
		}

		return videos;
	}
}
