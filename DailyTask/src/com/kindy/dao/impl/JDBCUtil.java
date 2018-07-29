package com.kindy.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JDBCUtil {
	private  static String url="jdbc:oracle:"+"thin:@127.0.0.1:1521:kindy";
	private  static String user="kindy";
	private  static String password="123";

	
	private JDBCUtil(){}
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void release(Connection conn,PreparedStatement psmt,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(psmt!=null){
					psmt.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					if(conn!=null){
						conn.close();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
