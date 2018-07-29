package com.kindy.domain;

import java.util.Date;

public class WeeklyReport {
	private int weeklyReportID;
	private Date startDate;
	private String comments;
	private int score;
	
	public int getWeeklyReportID() {
		return weeklyReportID;
	}
	public void setWeeklyReportID(int weeklyReportID) {
		this.weeklyReportID = weeklyReportID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}		
}
