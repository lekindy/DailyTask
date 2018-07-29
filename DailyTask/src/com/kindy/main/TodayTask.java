package com.kindy.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.kindy.common.CommonFunc;
import com.kindy.dao.DailyTaskDao;
import com.kindy.dao.impl.DailyTaskDaoImpl;
import com.kindy.domain.Task;

public class TodayTask {
	
	public static void enterTodayTaskOperation(){
		System.out.println();
		//获取今天的记录
		List<Task> todayTaskList=getTodayTask();
		if(todayTaskList!=null&&!todayTaskList.isEmpty()){
			System.out.println("--------------今天的任务记录情况----------------");
			CommonFunc.DisplayTaskList(todayTaskList);
			System.out.println();
			System.out.println("是否修改今天的任务记录？(输入Y:修改;其他:回到主菜单)");
			Scanner scan=new Scanner(System.in);
			String modifyInfo=scan.nextLine();
			if(modifyInfo.equalsIgnoreCase("Y")){
				//update
				createOrUpdateDailyTask(false);
			}
		}
		else{
			//create
			createOrUpdateDailyTask(true);
		}
		
	}
	
	private static List<Task> getTodayTask(){
		DailyTaskDao dailyTaskDao=new DailyTaskDaoImpl();
		List<Task> todayTaskList=dailyTaskDao.getTodayTaskList();
		
		return todayTaskList;
	}
	
	private static boolean addNewTask(Task task){
		if(task==null){
			return false;
		}
		DailyTaskDao dailyTaskDao=new DailyTaskDaoImpl();
		return dailyTaskDao.addNewDailyTask(task);		
	}
	
	private static boolean updateDailyTask(Task task){
		if(task==null){
			return false;
		}
		DailyTaskDao dailyTaskDao=new DailyTaskDaoImpl();
		return dailyTaskDao.updateTodayDailyTask(task);	
	}
	
	private static int StringToInt(String code){
		int result=0;
		if(code==null||code.isEmpty()){
			return result;
		}
		result=Integer.parseInt(code);
		return result;
	}
	
	private static void createOrUpdateDailyTask(boolean isCreate){
		System.out.println();
		if(isCreate){
			System.out.println("--------------开始创建今天的任务记录-------------");
		}
		else{
			System.out.println("--------------开始修改今天的任务记录-------------");
		}

		Task recordTask=recordDailyTask();
		if(recordTask==null){
			return;
		}		
		System.out.println("录入的今日任务如下：");
		List<Task> tempTaskList=new ArrayList<>();

		tempTaskList.add(recordTask);
		CommonFunc.DisplayTaskList(tempTaskList);
		System.out.println("是否保存？(输入Y:保存; 其他:放弃录入结果)");
		
		Scanner scan=new Scanner(System.in);
		String saveInfo=scan.nextLine();
		if(saveInfo.equalsIgnoreCase("Y")){
			if(isCreate){
				
				if(addNewTask(recordTask)){
					System.out.println("创建成功!返回主菜单");
				}
				else{
					System.out.println("创建失败!重新创建任务记录。");
					createOrUpdateDailyTask(true);
				}
			}
			else{
				if(updateDailyTask(recordTask)){
				  System.out.println("修改成功!返回主菜单");
				}
				else{
					System.out.println("重新修改任务记录。");
					createOrUpdateDailyTask(false);
				}			
			}

		}
		else{			
			System.out.println("放弃录入结果，回到主菜单");
		}
	}
	
	
	private static Task recordDailyTask(){
		Task task=new Task();
		Scanner scan=null;
		String finishCode=null;
		
		System.out.println("学习完成?(输入1完成;输入0表示未完成;输入非数字放弃记录，回到主菜单)");
		scan=new Scanner(System.in);
		finishCode=scan.nextLine();
		try{
			task.setStudy(StringToInt(finishCode));
		}
		catch(NumberFormatException e){
			return null;
		}
		
		System.out.println("英语完成?(输入1完成;输入0表示未完成;输入非数字放弃记录，回到主菜单)");
		scan=new Scanner(System.in);
		finishCode=scan.nextLine();
		try{
			task.setEnglish(StringToInt(finishCode));
		}
		catch(NumberFormatException e){
			return null;
		}

		
		System.out.println("运动完成?(输入1完成;输入0表示未完成;输入非数字放弃记录，回到主菜单)");
		scan=new Scanner(System.in);
		finishCode=scan.nextLine();
		try{
			task.setSport(StringToInt(finishCode));
		}
		catch(NumberFormatException e){
			return null;
		}
		
		task.setUpdateTime(new Date());
		return task;
	}
	

}
