package com.kindy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kindy.dao.DailyTaskDao;
import com.kindy.domain.QueryCondition;
import com.kindy.domain.Task;

public class DailyTaskDaoImpl implements DailyTaskDao{

	@Override
	public List<Task> getTodayTaskList() {
		List<Task> taskList=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select TASK_ID,STUDY,ENGLISH,SPORT,UPD_DT from task where "
				+ "to_char(UPD_DT,'yyyy-MM-dd')=to_char(sysdate,'yyyy-MM-dd')";
		
		try {
			 conn=JDBCUtil.getConnection();
			 psmt=conn.prepareStatement(sql);
			 rs=psmt.executeQuery();
			 while(rs.next()){
				 Task task=new Task();
				 task.setTaskID(rs.getInt(1));
				 task.setStudy(rs.getInt(2));
				 task.setEnglish(rs.getInt(3));
				 task.setSport(rs.getInt(4));
				 task.setUpdateTime(rs.getTimestamp(5));
				 
				 taskList.add(task);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			JDBCUtil.release(conn, psmt, rs);
		}
		return taskList;
	}

	@Override
	public boolean addNewDailyTask(Task task) {
		boolean isSuccess=false;
		if(task==null){
			return isSuccess;
		}
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="insert into TASK(TASK_ID,STUDY,ENGLISH,SPORT,UPD_DT) "
				+ "values(TASK_ID_SEQ.NEXTVAL,?,?,?,sysdate)";
		
		try {
			 conn=JDBCUtil.getConnection();
			 psmt=conn.prepareStatement(sql);
			 psmt.setInt(1,task.getStudy());
			 psmt.setInt(2,task.getEnglish());
			 psmt.setInt(3,task.getSport());
			 psmt.executeUpdate();
			 isSuccess=true;
			 
		} catch (SQLException e) {
			isSuccess=false;
			e.printStackTrace();
		}
		finally{
			JDBCUtil.release(conn, psmt, rs);
		}
		return isSuccess;
		
	}

	@Override
	public boolean updateTodayDailyTask(Task task) {
		boolean isSuccess=false;
		if(task==null){
			return isSuccess;
		}
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="update TASK set STUDY=?,ENGLISH=?,SPORT=? "
				+ "where to_char(UPD_DT,'yyyy-MM-dd')=to_char(sysdate,'yyyy-MM-dd')";
		
		try {
			 conn=JDBCUtil.getConnection();
			 psmt=conn.prepareStatement(sql);
			 psmt.setInt(1,task.getStudy());
			 psmt.setInt(2,task.getEnglish());
			 psmt.setInt(3,task.getSport());
			 psmt.executeUpdate();
			 isSuccess=true;
			 
		} catch (SQLException e) {
			isSuccess=false;
			e.printStackTrace();
		}
		finally{
			JDBCUtil.release(conn, psmt, rs);
		}
		return isSuccess;
	}

	@Override
	public List<Task> getTaskListByCondition(QueryCondition condition) {
		List<Task> taskList=new ArrayList<>();
		if(condition==null){
			taskList.clear();
			return taskList;
		}
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select TASK_ID,STUDY,ENGLISH,SPORT,UPD_DT from task where "
				+ "to_char(UPD_DT,'yyyy-MM-dd')>? and to_char(UPD_DT-?,'yyyy-MM-dd')<?";
		
		try {
			 conn=JDBCUtil.getConnection();
			 psmt=conn.prepareStatement(sql);
			 psmt.setString(1, condition.getStartDateTime());
			 psmt.setInt(2, condition.getDuration());
			 psmt.setString(3, condition.getStartDateTime());
			 rs=psmt.executeQuery();
			 while(rs.next()){
				 Task task=new Task();
				 task.setTaskID(rs.getInt(1));
				 task.setStudy(rs.getInt(2));
				 task.setEnglish(rs.getInt(3));
				 task.setSport(rs.getInt(4));
				 task.setUpdateTime(rs.getTimestamp(5));
				 
				 taskList.add(task);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			JDBCUtil.release(conn, psmt, rs);
		}
		return taskList;
	}


}
