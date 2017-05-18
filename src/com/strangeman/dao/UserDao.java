package com.strangeman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.strangeman.database.DBUtils;
import com.strangeman.entity.User;

public class UserDao {
	private DBUtils dbUtils;
	private Connection connection;
	
	public UserDao(Connection connection){
		this.connection=connection;
		dbUtils=DBUtils.getDBUtils();
	}
	
	public User login(String userId,String password) throws SQLException{
		String sql="select * from user where userid = ? and password = ?";
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User user=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				String userName=resultSet.getString("username");
				String userPhoto=resultSet.getString("userphoto");
				user= new User(userId, userName, userPhoto);
			}
		}finally{
			dbUtils.closeStatement(preparedStatement);
		}
		return user;
	}
}
