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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Video")
public class Video {
	@Column(name = "Id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Title", nullable = false, columnDefinition = "NVARCHAR (255)")
	private String title;

	@Column(name = "Poster", nullable = false, length = 255)
	private String poster;

	@Column(name = "Views", nullable = false)
	private int views;

	@Column(name = "Description", nullable = false, columnDefinition = "NVARCHAR (2000)")
	private String desc;

	@Column(name = "Active", nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "videoYoutube")
	private List<Share> shares;
}
