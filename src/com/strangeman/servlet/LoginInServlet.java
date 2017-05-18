package com.strangeman.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.strangeman.entity.Question;
import com.strangeman.entity.QuestionMethod;
import com.strangeman.entity.User;
import com.strangeman.service.Service;

public class LoginInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private GsonBuilder builder;
	private String jsonTest;
	private Service service;
	private User user;
	public LoginInServlet(){
		super();
	}
    public void destroy(){
    	super.destroy();
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	this.doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	response.setContentType("text/html;charset=utf-8");
    	request.setCharacterEncoding("UTF-8");
    	try{
			
			PrintWriter writer = null;
	        try {
	            writer = response.getWriter();

	            builder=new GsonBuilder();
	            gson=builder.create();
	            service=Service.getService();
	            
	            String userId=request.getParameter("userId");
	            String passwd=request.getParameter("passwd");
	            
	            user=service.login(userId, passwd);
	            
	            
	            jsonTest=gson.toJson(user, User.class);
	        
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

