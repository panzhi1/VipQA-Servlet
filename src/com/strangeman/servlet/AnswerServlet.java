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
import com.strangeman.domain.Answer;
import com.strangeman.domain.Question;
import com.strangeman.domain.QuestionMethod;

public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest,jsonListTest;
    private Answer answer;
    private String answerId;
  
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
    
			builder=new GsonBuilder();
            gson=builder.create();
            
            String answerJson=request.getParameter("answerJson");
            answer=gson.fromJson(answerJson,Answer.class);
            
			try{
				
					PrintWriter writer = null;
			        try {
			            writer = response.getWriter();
                       
			            answerId="546";
			            
			            jsonTest=gson.toJson(answerId,String.class);
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
