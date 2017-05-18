package com.strangeman.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.strangeman.entity.Question;
import com.strangeman.entity.QuestionMethod;
import com.strangeman.service.Service;


public class AskQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest;
    private Question question;
    private String questionId;
    private Service service;
    private QuestionMethod questionMethod;
		public AskQuestionServlet(){
			super();
		}
		public void  destroy(){
		   super.destroy();
		}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        this.doPost(request, response);
	    }
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			builder=new GsonBuilder();
            gson=builder.create();
            service=Service.getService();
            questionMethod=new QuestionMethod();
            
			String questionJson=request.getParameter("question");
			question=gson.fromJson(questionJson, Question.class);
			System.out.println(question.getContent());
			service.addQuestion(question.getUserId(), question.getProductId(), question.getContent());
		    questionMethod.setQuestions(service.getQuestion(question.getProductId()));
			//String questionId=service.addQuestion(question.getUserId(), question.getProductId(), question.getContent());
			try{
				
					PrintWriter writer = null;
			        try {
			            writer = response.getWriter();

			            jsonTest=gson.toJson(questionMethod,QuestionMethod.class);
			            writer.write(jsonTest);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }finally{
			            writer.flush();
			            writer.close();
			        }
					
				
				}catch(Exception e){
					System.out.println(e);
				}
		}
}
