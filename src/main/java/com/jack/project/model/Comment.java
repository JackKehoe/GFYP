package com.jack.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	
	private int id;
	private String content;
	private Date timeStamp;
	
	private User user;
	private Report report;
	
	public Comment(){
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="report_id")
	public Report getReport() {
		return report;
	}
	
	
	public void setReport(Report report) {
		this.report = report;
	}
}
