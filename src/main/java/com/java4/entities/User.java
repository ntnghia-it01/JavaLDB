package com.java4.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Tạo hàm xây dựng không tham số
@AllArgsConstructor // Tạo hàm xây dựng có nhiều tham số
@Data // Tự tạo Getter/Setter
@Entity // Đăng ký class thành entity
@Table(name = "User") // Đăng ký class nhận thông tin từ bảng User trong db
public class User {
//	Đăng ký cột có tên là Id và không null cho biến id 
	@Column(name = "Id", nullable = false)
	@Id // Đánh dấu khoá chính
//	Sẽ cho id tự tăng
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Password", nullable = false, length = 100)
	private String password;

	@Column(name = "Email", nullable = false, length = 100)
	private String email; // => varchar ở db

	@Column(name = "Fullname", nullable = false, columnDefinition = "NVARCHAR (100)")
	private String fullName;

	@Column(name = "Admin", nullable = false)
	private boolean admin;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "user")
	private List<Share> shares;

}
