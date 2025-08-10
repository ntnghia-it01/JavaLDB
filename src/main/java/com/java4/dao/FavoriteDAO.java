package com.java4.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.java4.config.DBConnection;
import com.java4.entities.Favorite;

public class FavoriteDAO {

	public static boolean insert(Favorite favorite) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.persist(favorite);

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

	public static boolean update(Favorite favorite) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.merge(favorite);

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

	public static boolean delete(Favorite favorite) {
		EntityManager manager = DBConnection.getEntityManager();
		try {
			if (!manager.getTransaction().isActive()) {
				manager.getTransaction().begin();
			}

			manager.remove(manager.merge(favorite));

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

	public static Favorite checkFavoriteVideo(int userId, int videoId) {
		EntityManager manager = DBConnection.getEntityManager();
		try {

			String sql = "SELECT * FROM favorites WHERE user_id=?1 AND video_id=?2";
			Query query = manager.createNativeQuery(sql, Favorite.class);
			query.setParameter(1, userId);
			query.setParameter(2, videoId);

			return (Favorite) query.getSingleResult();
		} catch (Exception e) {
			manager.close();
			return null;
		}
	}

//	CREATE TABLE [dbo].[favorites] (
//		    [id]        INT           IDENTITY (1, 1) NOT NULL,
//		    [like_date] DATETIME2 (7) NOT NULL,
//		    [user_id]   INT           NULL,
//		    [video_id]  INT           NULL,
//		    PRIMARY KEY CLUSTERED ([id] ASC),
//		    CONSTRAINT [FK_Favorite_User] FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id]),
//		    CONSTRAINT [FKiavc6ahbceco5oimdp56ylugl] FOREIGN KEY ([video_id]) REFERENCES [dbo].[videos] ([id])
//		);
//

}
