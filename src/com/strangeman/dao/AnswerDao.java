package com.strangeman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.strangeman.database.DBUtils;
import com.strangeman.entity.Answer;

public class AnswerDao {
	private DBUtils dbUtils;
	private Connection connection;
	public AnswerDao(Connection connection){
		dbUtils=DBUtils.getDBUtils();
		this.connection=connection;
	}
	
	public String addAnswer(String questionId,String userId,String answer) throws SQLException{
		String sql="insert into ANSWER (QUESTIONID,USERID,ANSWER,ANSWERID,ANSWERDATE) values (?,?,?,?,?)";
		PreparedStatement pStatement=null;
		Random random=new Random();
		String intNum=String.valueOf(random.nextInt(10000));
		while(intNum.length()<4){
			intNum="0"+intNum;
		}
		String answerId=String.valueOf(System.currentTimeMillis())+intNum;
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String answerDate=format.format(date);
		
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, questionId);
			pStatement.setString(2, userId);
			pStatement.setString(3, answer);
			pStatement.setString(4, answerId);
			pStatement.setString(5, answerDate);
			
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
		return answerId;
	}
	
	public void deleteAnswer(String answerId) throws SQLException{
		String sql="delete from ANSWER where ANSWERID=?";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, answerId);
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
	}
	
	public void updateAnswer(String answerId,String answer) throws SQLException{
		String sql="update ANSWER set ANSWER=? where answerId=?";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, answer);
			pStatement.setString(2, answerId);
			pStatement.executeUpdate();
		} finally {
			dbUtils.closeStatement(pStatement);
		}
	}
	
	public List<Answer> getAnswer(String questionId) throws SQLException{
		List<Answer> answers=null;
		Answer answer=null;
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		String sql="select * from ANSWER where QUESTIONID = ?";
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, questionId);
			resultSet=pStatement.executeQuery();
			while(resultSet.next()){
				if(answers==null)
					answers=new ArrayList<>();
				String answerId=resultSet.getString("answerid");
				String userId=resultSet.getString("userid");
				String answerString=resultSet.getString("answer");
				String answerDate=resultSet.getString("answerdate");
				answer=new Answer(questionId, answerId, userId, answerString,answerDate);
				answers.add(answer);
			}
		} finally {
			dbUtils.closeStatement(pStatement);
		}
		return answers;
	}
}