package com.java4.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.java4.config.DBConnection;
import com.java4.entities.User;

public class UserDAO {
//	insert, update, delete 

	public static boolean insert(User user) {
//		Khởi tạo kết nối db
		EntityManager manager = DBConnection.getEntityManager();
		try {
//			Khởi tạo transaction 
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

//			Insert DB
			manager.persist(user);

//			Đẩy transaction lên db để xử lý
			manager.getTransaction().commit();

//			Ngắt kết nối DB 
			manager.close();
			return true;
		} catch (Exception e) {
//			Loại bỏ lệnh sql khỏi transaction
			manager.getTransaction().rollback();
//			Ngắt kết nối DB 
			manager.close();
			return false;
		}
	}

	public static boolean update(User user) {
//		Khởi tạo kết nối db
		EntityManager manager = DBConnection.getEntityManager();
		try {
//			Khởi tạo transaction 
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

//			Update DB
			manager.merge(user);

//			Đẩy transaction lên db để xử lý
			manager.getTransaction().commit();

//			Ngắt kết nối DB 
			manager.close();
			return true;
		} catch (Exception e) {
//			Loại bỏ lệnh sql khỏi transaction
			manager.getTransaction().rollback();
//			Ngắt kết nối DB 
			manager.close();
			return false;
		}
	}

	public static boolean delete(User user) {
//		Khởi tạo kết nối db
		EntityManager manager = DBConnection.getEntityManager();
		try {
//			Khởi tạo transaction 
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

//			Delete DB
			manager.remove(user);

//			Đẩy transaction lên db để xử lý
			manager.getTransaction().commit();

//			Ngắt kết nối DB 
			manager.close();
			return true;
		} catch (Exception e) {
//			Loại bỏ lệnh sql khỏi transaction
			manager.getTransaction().rollback();
//			Ngắt kết nối DB 
			manager.close();
			return false;
		}
	}

//	Kiểm tra email có tồn tại chưa?

	public static boolean checkEmailExist(String email) {
//	SELECT * FROM user WHERE user.Email='abc@gmail.com'

//		Khởi tạo kết nối db
		EntityManager manager = DBConnection.getEntityManager();
		try {
//			String sql = "SELECT * FROM User u WHERE u.email=?1";
//			TypedQuery<User> user = manager.createQuery(sql, null)

			String sql = "SELECT * FROM user WHERE Email=?1";
			Query query = manager.createNativeQuery(sql, User.class);
			query.setParameter(1, email);

//			query.getSingleResult(); // Trả 1 đối tượng entity
//			query.getResultList(); // Trả về danh sách entity

			manager.close();
			return query.getSingleResult() != null;
		} catch (Exception e) {
			manager.close();
			return false;
		}
	}

	public static boolean login(String email, String password) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			String sql = "SELECT * FROM user WHERE Email=?1";
			Query query = manager.createNativeQuery(sql, User.class);
			query.setParameter(1, email);

			if (query.getSingleResult() == null) {
//				Không tìm thấy email 
				manager.close();
				return false;
			}

			User user = (User) query.getSingleResult();

			if (user.getPassword().equals(password)) {
				manager.close();
				return true;
			}

			manager.close();
			return false;

		} catch (Exception e) {
			manager.close();
			return false;
		}
	}
}
