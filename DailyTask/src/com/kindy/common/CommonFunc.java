package com.kindy.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kindy.domain.Task;

public class CommonFunc {
	public static void DisplayTaskList(List<Task> taskList){
		System.out.println("学习"+Constants.TabDelimiter+"英语"+Constants.TabDelimiter
				          +"运动"+Constants.TabDelimiter+"记录时间 ");
		if(taskList==null||taskList.isEmpty()){
			return;
		}
		
//		for(int i=0;i<taskList.size();i++){
//			Task todayTask=taskList.get(i);
//		}
		for(Task todayTask:taskList){
			System.out.println(todayTask.getCompleteCondition(todayTask.getStudy())+Constants.TabDelimiter+
					todayTask.getCompleteCondition(todayTask.getEnglish())+Constants.TabDelimiter+
					todayTask.getCompleteCondition(todayTask.getSport())+Constants.TabDelimiter+
					getFormatDate(todayTask.getUpdateTime())+Constants.TabDelimiter			
					);
		}
	}
	
	public static String getFormatDate(Date dateTime){
		if(dateTime==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate=sdf.format(dateTime);
		return formatDate;
	}

}
