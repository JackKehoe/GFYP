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
@Table(name="rating")
public class Rating {
	
	
	private int id;
	private String reportRating;

	private Report report;
	
	public Rating(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReportRating() {
		return reportRating;
	}

	public void setReportRating(String reportRating) {
		this.reportRating = reportRating;
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