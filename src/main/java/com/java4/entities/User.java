package com.java4.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Tạo hàm xây dựng không tham số
@AllArgsConstructor // Tạo hàm xây dựng có nhiều tham số
@Data // Tự tạo Getter/Setter
@Entity // Đăng ký class thành entity
@Table(name = "users") // Đăng ký class nhận thông tin từ bảng User trong db
public class User {
//	Đăng ký cột có tên là Id và không null cho biến id 
	@Column(name = "id", nullable = false)
	@Id // Đánh dấu khoá chính
//	Sẽ cho id tự tăng
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "email", nullable = false, length = 100)
	private String email; // => varchar ở db

	@Column(name = "full_name", nullable = false, columnDefinition = "NVARCHAR (100)")
	private String fullName;

	@Column(name = "admin", nullable = false)
	private boolean admin;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "user")
	private List<Share> shares;

}
