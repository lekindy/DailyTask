package com.kindy.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kindy.common.CommonFunc;
import com.kindy.dao.DailyTaskDao;
import com.kindy.dao.impl.DailyTaskDaoImpl;
import com.kindy.domain.QueryCondition;
import com.kindy.domain.Task;

public class HistoryTask {
	public static void enterHistoryTaskOperation(){
		System.out.println();
		System.out.println("------历史任务记录查询------");
		QueryCondition queryCondition=new QueryCondition();
		String startDateTime=entryStartDate();
		if(startDateTime==null){
			return;
		}
		queryCondition.setStartDateTime(startDateTime);
		int duration=entryDuration();
		if(duration<0){
			return;
		}
		queryCondition.setDuration(duration);
		List<Task> historyTaskList=getHistoryTaskList(queryCondition);
		CommonFunc.DisplayTaskList(historyTaskList);
		System.out.println();
		System.out.println("输入E:返回主菜单;其他，重新查询历史记录");
		Scanner scan=new Scanner(System.in);
		if(scan.nextLine().equalsIgnoreCase("E")){
			return;
		}
		else{
			enterHistoryTaskOperation();			
		}

	}
	
	private static String entryStartDate(){
		System.out.println("请输入查询的起始日期(yyyy-MM-dd形式);输入:E，退出查询，返回主菜单；");
		Scanner scan=new Scanner(System.in);
		String startDateInfo=scan.nextLine();
		if(startDateInfo.equalsIgnoreCase("E")){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(startDateInfo);
		} catch (ParseException e) {
			System.out.println("日期格式输入错误，请重新输入.");
			entryStartDate();			
		}
		return startDateInfo;
	}
	
	private static int entryDuration(){
		int inputCode=-1;
		System.out.println("请输入查询时间范围(请输入数字):");
		System.out.println("1：7天");
		System.out.println("2:30天");
	    System.out.println("其他:退出查询，返回主菜单");
	    Scanner scan=new Scanner(System.in);
	    String durationInfo=scan.nextLine();
	    try{
	    	inputCode=Integer.parseInt(durationInfo);
	    }
	    catch(Exception e){
	    	return -1;
	    }
	    switch(inputCode){
	    case 1:
	    	return 7;
	    case 2:
	    	return 30;	    
	    default:
	    	return -1;
	    }
	}

	private static List<Task> getHistoryTaskList(QueryCondition condition){
		List<Task> historyTaskList=new ArrayList<>();
		if(condition==null){
			historyTaskList.clear();
			return historyTaskList;
		}
		DailyTaskDao dailyTaskDao=new DailyTaskDaoImpl();
		return dailyTaskDao.getTaskListByCondition(condition);
	}
}
