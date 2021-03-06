package com.strangeman.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.strangeman.entity.Question;
import com.strangeman.entity.QuestionMethod;
import com.strangeman.service.Service;

public class AllQuestionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest;
	private QuestionMethod questionMethod;
	private Service service;
	private Question question;
	private List<Question> questionList;
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
		    //String productId=request.getParameter("productId");
		    //System.out.println(productId);
			try{
				
					PrintWriter writer = null;
			        try {
			            writer = response.getWriter();

			            builder=new GsonBuilder();
			            gson=builder.create();
			            questionList=new ArrayList<>();
//			            service=Service.getService();
			            questionMethod=new QuestionMethod();
			            System.out.println(1);
//                        questionMethod.setQuestions(service.getQuestion(productId));
			            question=new Question("123","456","789","852","798");
			            System.out.println(2);
			            questionList.add(question);
			            System.out.println(3);
			            questionMethod.setQuestions(questionList);
			            System.out.println(4);
			            jsonTest=gson.toJson(questionMethod, QuestionMethod.class);
			            System.out.println(5);
			            /**
			             * д��json
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
