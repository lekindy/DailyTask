package com.kindy.domain;

import java.util.Date;

public class Task {
	private int taskID;
	private int study;
	private int english;
	private int sport;
	private Date updateTime;
	
	
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getStudy() {
		return study;
	}
	public void setStudy(int study) {
		this.study = study;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getSport() {
		return sport;
	}
	public void setSport(int sport) {
		this.sport = sport;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCompleteCondition(int point){
		if(point>0){
			return "完成";
		}
		else{
			return "未完成";
		}
	}
	
	

}
