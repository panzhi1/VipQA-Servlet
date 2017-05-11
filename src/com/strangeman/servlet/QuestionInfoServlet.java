package com.strangeman.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.strangeman.domain.*;


public class QuestionInfoServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
private Gson gson;
private GsonBuilder builder;
private String jsonTest;
private Question question;


	public QuestionInfoServlet(){
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
		 String productId=request.getParameter("productId");
		 String questionId=request.getParameter("questionId");
		try{
			
				PrintWriter writer = null;
		        try {
		            writer = response.getWriter();
                    
		            builder=new GsonBuilder();
		            gson=builder.create();
		            question=new Question("111","≤ª∫√”√∞…",productId,"123456789","5.11");
		           
		            jsonTest=gson.toJson(question, Question.class);
		         
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
