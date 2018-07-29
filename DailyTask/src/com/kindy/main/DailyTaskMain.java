package com.kindy.main;

import java.util.Scanner;

import com.kindy.common.Constants;

public class DailyTaskMain {
	private static final int TODAY_TASK=1;
	public static void main(String args[]) throws InterruptedException{
		System.out.println("Welcome to Dail1y Task!");
		boolean exit=false;
		while(!exit){
			showMainPage();
			Scanner scan=new Scanner(System.in);
			String operateInfo=scan.nextLine();
			int operateNumber=-1;
			try{
				operateNumber=Integer.parseInt(operateInfo);
			}
			catch(Exception e){
				System.out.println(Constants.InputValidNumber);//万一输入数字以外
				continue;
			}
			switch(operateNumber){
			case TODAY_TASK:
				TodayTask.enterTodayTaskOperation();
			    break;
			case 2:
				HistoryTask.enterHistoryTaskOperation();
			    break;
			case 3:
				System.out.println("enter Weekly Report Modify");
				break;
			case 4:
				System.out.println("enter Weekly Report History");
				break;
			case 5:
				exit=true;
				continue;
			default:
				System.out.println(Constants.InputValidNumber);
				continue;
			}
			//Thread.sleep(500);
		}

	}
	
	public static void showMainPage(){
		System.out.println();
		System.out.println("------主菜单-------");
		System.out.println("请选择你要执行的操作(输入数字)：");
		System.out.println("1.新建或修改今日记录");
		System.out.println("2.查询历史每日记录");
		System.out.println("3.新建或修改每周评价");
		System.out.println("4.查看历史每周评价");
		System.out.println("5.退出程序");
	}

}
