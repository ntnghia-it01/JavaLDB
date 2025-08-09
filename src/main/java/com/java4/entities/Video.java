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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class Video {
	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title", nullable = false, columnDefinition = "NVARCHAR (255)")
	private String title;

	@Column(name = "poster", nullable = false, length = 255)
	private String poster;

	@Column(name = "url", nullable = false, length = 255)
	private String url;

	@Column(name = "views", nullable = false)
	private int views;

	@Column(name = "description", nullable = false, columnDefinition = "NVARCHAR (2000)")
	private String desc;

	@Column(name = "active", nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "videoYoutube")
	private List<Share> shares;

}

// giá trị ở JPS được lấy từ getter 
// getId => video.getId()
// jsp => video.id: Loại bỏ chữ "get" các ký tự phía sau chữ get
// chuyển về đúng định dạng đặt tên biến của java 
// getUrlImage => video.urlImage 
