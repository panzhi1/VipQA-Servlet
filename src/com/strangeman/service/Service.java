package com.strangeman.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.strangeman.dao.AnswerDao;
import com.strangeman.dao.OrderDao;
import com.strangeman.dao.ProductDao;
import com.strangeman.dao.QuertionDao;
import com.strangeman.dao.UserDao;
import com.strangeman.database.DBUtils;
import com.strangeman.entity.Order;
import com.strangeman.entity.Product;
import com.strangeman.entity.Question;
import com.strangeman.entity.QuestionPreview;
import com.strangeman.entity.User;

public class Service {

	private final DBUtils dbUtils;

	private Service() {
		dbUtils = DBUtils.getDBUtils();
	}

	private static class ServiceHelper {
		private static final Service SERVICE = new Service();
	}

	public static Service getService() {
		return ServiceHelper.SERVICE;
	}

	public String addQuestion(String userId, String productId, String theme) {
		String questionId = null;
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			QuertionDao quertionDao = new QuertionDao(connection);
			questionId = quertionDao.addQuestion(userId, productId, theme);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			return null;
		} finally {
			dbUtils.closeConnection(connection);
		}
		return questionId;
	}

	public String addAnswer(String questionId, String userId, String answer) {
		String answerId = null;
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			AnswerDao answerDao = new AnswerDao(connection);
			answerId = answerDao.addAnswer(questionId, userId, answer);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			return null;
		} finally {
			dbUtils.closeConnection(connection);
		}
		return answerId;
	}

	public void deleteQuestion(String questionId) {
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			QuertionDao quertionDao = new QuertionDao(connection);
			quertionDao.deleteQuestion(questionId);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	public void deleteAnswer(String answerId) {
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			AnswerDao answerDao = new AnswerDao(connection);
			answerDao.deleteAnswer(answerId);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	public void updateQuestion(String questionId, String theme) {
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			QuertionDao quertionDao = new QuertionDao(connection);
			quertionDao.updateTheme(questionId, theme);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	public void updateAnswer(String answerId, String answer) {
		Connection connection = null;
		try {
			connection = dbUtils.getConnection();
			AnswerDao answerDao = new AnswerDao(connection);
			answerDao.updateAnswer(answerId, answer);
			dbUtils.commit(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
	}

	public QuestionPreview getPreview(String productId) {
		List<Question> questions = getQuestion(productId);
		if (questions == null || questions.size() == 0)
			return null;
		int questionNum = questions.size();
		QuestionPreview questionPreview;
		if (questionNum == 1) {
			if (questions.get(0).getAnswerList() != null)
				questionPreview = new QuestionPreview(questionNum, questions.get(0).getContent(),
						questions.get(0).getAnswerList().size(), null, 0);
			else
				questionPreview = new QuestionPreview(questionNum, questions.get(0).getContent(), 0, null, 0);
		} else {
			if (questions.get(0).getAnswerList() != null)
				if (questions.get(1).getAnswerList() != null)
					questionPreview = new QuestionPreview(questionNum, 
							questions.get(0).getContent(),questions.get(0).getAnswerList().size(), 
							questions.get(1).getContent(),questions.get(1).getAnswerList().size());
				else 
					questionPreview=new QuestionPreview(questionNum, 
							questions.get(0).getContent(), questions.get(0).getAnswerList().size(), 
							questions.get(1).getContent(), 0);
			else
				questionPreview=new QuestionPreview(questionNum, 
						questions.get(0).getContent(), 0, 
						questions.get(1).getContent(), 0);
		}
		return questionPreview;
	}
	
	public Question getAQuestion(String questionId){
		Question question=null;
		Connection connection=null;
		try {
			connection=dbUtils.getConnection();
			
			QuertionDao quertionDao=new QuertionDao(connection);
			question=quertionDao.getAQuestion(questionId);
			if(question==null)
				return null;
			AnswerDao answerDao=new AnswerDao(connection);
			question.setAnswerList(answerDao.getAnswer(questionId));
			dbUtils.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			dbUtils.closeConnection(connection);
		}
		return question;
	}

	public List<Question> getQuestion(String productId) {
		List<Question> questions = null;

		Connection connection = null;
		try {
			connection = dbUtils.getConnection();

			QuertionDao quertionDao = new QuertionDao(connection);
			questions = quertionDao.getQuestion(productId);
			if (questions == null)
				return null;
			AnswerDao answerDao = new AnswerDao(connection);
			for (int i = 0; i < questions.size(); i++) {
				Question question = questions.get(i);
				question.setAnswerList(answerDao.getAnswer(question.getQuestionId()));
			}
			dbUtils.commit(connection);
			
			Collections.sort(questions,new MySort());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbUtils.closeConnection(connection);
		}
		return questions;
	}
	
	public User login(String userId,String password){
		User user=null;
		Connection connection=null;
		try {
			connection=dbUtils.getConnection();
			
			UserDao userDao=new UserDao(connection);
			user=userDao.login(userId, password);
			dbUtils.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			dbUtils.closeConnection(connection);
		}
		return user;
	}
	
	public Product getAProduct(String productId){
		Product product=null;
		Connection connection=null;
		try {
			connection=dbUtils.getConnection();
			
			ProductDao productDao=new ProductDao(connection);
			product=productDao.getAProduct(productId);
			dbUtils.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			dbUtils.closeConnection(connection);
		}
		return product;
	}
	
	public List<Product> getAllPeoduct(){
		List<Product> products=null;
		Connection connection=null;
		try {
			connection = dbUtils.getConnection();

			ProductDao productDao=new ProductDao(connection);
			products = productDao.getAllProduct();
			dbUtils.commit(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbUtils.closeConnection(connection);
		}
		return products;
	}
	
	public List<Order> getOrders(String userId){
		List<Order> orders=null;
		Connection connection=null;
		try {
			connection=dbUtils.getConnection();
			
			OrderDao orderDao=new OrderDao(connection);
			orders=orderDao.getOrders(userId);
			dbUtils.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			dbUtils.closeConnection(connection);
		}
		return orders;
	}
}

class MySort implements Comparator<Question>{
	@Override
	public int compare(Question o1, Question o2) {
		if(o1.getAnswerList()==null)
			return 1;
		if(o2.getAnswerList()==null)
			return -1;
		if(o1.getAnswerList().size()<o2.getAnswerList().size())
			return 1;
		if(o1.getAnswerList().size()>o2.getAnswerList().size())
			return -1;
		return o1.getAskDate().compareTo(o2.getAskDate());
	}
}