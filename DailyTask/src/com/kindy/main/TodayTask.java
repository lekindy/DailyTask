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
		//��ȡ����ļ�¼
		List<Task> todayTaskList=getTodayTask();
		if(todayTaskList!=null&&!todayTaskList.isEmpty()){
			System.out.println("--------------����������¼���----------------");
			CommonFunc.DisplayTaskList(todayTaskList);
			System.out.println();
			System.out.println("�Ƿ��޸Ľ���������¼��(����Y:�޸�;����:�ص����˵�)");
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
			System.out.println("--------------��ʼ��������������¼-------------");
		}
		else{
			System.out.println("--------------��ʼ�޸Ľ���������¼-------------");
		}

		Task recordTask=recordDailyTask();
		if(recordTask==null){
			return;
		}		
		System.out.println("¼��Ľ����������£�");
		List<Task> tempTaskList=new ArrayList<>();

		tempTaskList.add(recordTask);
		CommonFunc.DisplayTaskList(tempTaskList);
		System.out.println("�Ƿ񱣴棿(����Y:����; ����:����¼����)");
		
		Scanner scan=new Scanner(System.in);
		String saveInfo=scan.nextLine();
		if(saveInfo.equalsIgnoreCase("Y")){
			if(isCreate){
				
				if(addNewTask(recordTask)){
					System.out.println("�����ɹ�!�������˵�");
				}
				else{
					System.out.println("����ʧ��!���´��������¼��");
					createOrUpdateDailyTask(true);
				}
			}
			else{
				if(updateDailyTask(recordTask)){
				  System.out.println("�޸ĳɹ�!�������˵�");
				}
				else{
					System.out.println("�����޸������¼��");
					createOrUpdateDailyTask(false);
				}			
			}

		}
		else{			
			System.out.println("����¼�������ص����˵�");
		}
	}
	
	
	private static Task recordDailyTask(){
		Task task=new Task();
		Scanner scan=null;
		String finishCode=null;
		
		System.out.println("ѧϰ���?(����1���;����0��ʾδ���;��������ַ�����¼���ص����˵�)");
		scan=new Scanner(System.in);
		finishCode=scan.nextLine();
		try{
			task.setStudy(StringToInt(finishCode));
		}
		catch(NumberFormatException e){
			return null;
		}
		
		System.out.println("Ӣ�����?(����1���;����0��ʾδ���;��������ַ�����¼���ص����˵�)");
		scan=new Scanner(System.in);
		finishCode=scan.nextLine();
		try{
			task.setEnglish(StringToInt(finishCode));
		}
		catch(NumberFormatException e){
			return null;
		}

		
		System.out.println("�˶����?(����1���;����0��ʾδ���;��������ַ�����¼���ص����˵�)");
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
