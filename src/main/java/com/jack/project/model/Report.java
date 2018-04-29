package com.jack.project.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="report")
public class Report {
	
	private int id;
	private String title;
	private String date;
	private String content;
	private String uploadFile;
	
	private User user;

	private List<Comment> comments;
	private List<Rating> ratings;
	
	public Report() {
		
	}
	
	public Report(String title, String date, String content) {
		super();
		this.title = title;
		this.date = date;
		this.content = content;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Comment> getComments() {
		return comments;
	}
	

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	public List<Rating> getRating() {
		return ratings;
	}
	
	public void setRating(List<Rating> ratings) {
		this.ratings = ratings;
	}


}


