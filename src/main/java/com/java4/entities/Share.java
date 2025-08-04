package com.java4.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Share")
public class Share {

	@Column(name = "Id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Emails", nullable = false, length = 255)
	private String email;

	@Column(name = "ShareDate", nullable = false)
	private Date shareDate;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video videoYoutube;
}
