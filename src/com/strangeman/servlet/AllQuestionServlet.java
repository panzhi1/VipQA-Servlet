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
import com.strangeman.domain.Question;
import com.strangeman.domain.QuestionMethod;

public class AllQuestionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest,jsonListTest;
	private QuestionMethod questionMethod;
		public AllQuestionServlet(){
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
			try{
				
					PrintWriter writer = null;
			        try {
			            writer = response.getWriter();

			            builder=new GsonBuilder();
			            gson=builder.create();
                        questionMethod=new QuestionMethod();
			           
			            jsonTest=gson.toJson(questionMethod, QuestionMethod.class);
			            /**
			             * –¥»Îjson
			             */
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
