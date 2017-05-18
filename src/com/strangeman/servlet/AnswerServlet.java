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
import com.strangeman.entity.Answer;
import com.strangeman.entity.Question;
import com.strangeman.entity.QuestionMethod;
import com.strangeman.service.Service;

public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest;
    private Answer answer;
    private Service service;
    private Question question;
		public AnswerServlet(){
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
			
            
			try{
				
					PrintWriter writer = null;
			        try {
			            writer = response.getWriter();
                       
			            builder=new GsonBuilder();
			            gson=builder.create();
			            service=Service.getService();
			            String answerJson=request.getParameter("answer");
			            answer=gson.fromJson(answerJson,Answer.class);
			            service.addAnswer(answer.getQuestionId(), answer.getUserId(), answer.getContent());
			            question=service.getAQuestion(answer.getQuestionId());
			            jsonTest=gson.toJson(question,Question.class);
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
