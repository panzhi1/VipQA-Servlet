package com.strangeman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private DBUtils(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
			e.printStackTrace();
		}
	}
	private static class DBUtilsHelper{
		private static final DBUtils dbUtils=new DBUtils();
	}
	public static final DBUtils getDBUtils(){
		return DBUtilsHelper.dbUtils;
	}
	
	public Connection getConnection()throws SQLException{
		return getConnection("jdbc:mysql://127.0.0.1:3306/vipqa","VipQA","jay123,,");
	}
	public Connection getConnection(String url,String username,String password)throws SQLException{
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url,username,password);
			if(connection!=null)
				connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("ConnectionFailed");
			e.printStackTrace();
			throw e;
		}
		return connection;
	}
	
	public void commit(Connection connection) throws SQLException{
		try {
			connection.commit();
		} catch (SQLException e) {
			rollBack(connection);
			System.out.println("CommitFailed");
			e.printStackTrace();
			throw e;
		}
	}
	private void rollBack(Connection connection){
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println("RollBackFailed");
			e.printStackTrace();
		}
	}
	
	public void closeStatement(Statement statement) throws SQLException{
		if(statement==null)
			return;
		try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("CloseStatementFailed");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void closeConnection(Connection connection){
		if(connection==null)
			return;
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("CloseConnectionFailed");
			e.printStackTrace();
		}
	}
}
