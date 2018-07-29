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
				System.out.println(Constants.InputValidNumber);//��һ������������
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
		System.out.println("------���˵�-------");
		System.out.println("��ѡ����Ҫִ�еĲ���(��������)��");
		System.out.println("1.�½����޸Ľ��ռ�¼");
		System.out.println("2.��ѯ��ʷÿ�ռ�¼");
		System.out.println("3.�½����޸�ÿ������");
		System.out.println("4.�鿴��ʷÿ������");
		System.out.println("5.�˳�����");
	}

}
