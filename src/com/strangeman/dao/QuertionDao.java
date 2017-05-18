package com.strangeman.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.strangeman.database.DBUtils;
import com.strangeman.entity.Question;

public class QuertionDao {
	private DBUtils dbUtils;
	private Connection connection;
	
	public QuertionDao(Connection connection) {
		dbUtils=DBUtils.getDBUtils();
		this.connection=connection;
	}
	
	public String addQuestion(String userId,String productId,String theme) throws SQLException{
		String sql="insert into QUESTION (USERID,PRODUCTID,THEME,QUESTIONID,ASKDATE) values (?,?,?,?,?)";
		PreparedStatement pStatement=null;
		
		Random random=new Random();
		String intNum=String.valueOf(random.nextInt(10000));
		while(intNum.length()<4){
			intNum="0"+intNum;
		}
		String questionId=String.valueOf(System.currentTimeMillis())+intNum;
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String askDate=format.format(date);
		
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userId);
			pStatement.setString(2, productId);
			pStatement.setString(3, theme);
			pStatement.setString(4, questionId);
			pStatement.setString(5, askDate);
			
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
		return questionId;
	}
	
	public void deleteQuestion(String questionId) throws SQLException{
		String sql="delete from QUESTION where QUESTIONID=?";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, questionId);
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
	}
	
	public void updateTheme(String questionId,String theme) throws SQLException{
		String sql="update QUESTION set THEME=? where questionId=?";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, theme);
			pStatement.setString(2, questionId);
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
	}
	
	public Question getAQuestion(String questionId) throws SQLException{
		Question question=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select * from QUESTION where QUESTIONID = ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, questionId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				String content=resultSet.getString("theme");
				String productId=resultSet.getString("productid");
				String userId=resultSet.getString("userid");
				String askDate=resultSet.getString("askdate");
				question=new Question(questionId, content, productId, userId, askDate);
			}
		}finally{
			dbUtils.closeStatement(preparedStatement);
		}
		return question;
	}
	
	public List<Question> getQuestion(String productId) throws SQLException{
		List<Question> questions=null;
		Question question=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		String sql="select * from QUESTION where PRODUCTID = ?";
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, productId);
			resultSet=pStatement.executeQuery();
			while(resultSet.next()){
				if(questions==null)
					questions=new ArrayList<>();
				String questionId=resultSet.getString("questionid");
				String userId=resultSet.getString("userid");
				String theme=resultSet.getString("theme");
				String askDate=resultSet.getString("askdate");
				question=new Question(questionId, theme, productId,userId,askDate);
				questions.add(question);
			}
		} finally {
			dbUtils.closeStatement(pStatement);
		}
		return questions;
	}
}