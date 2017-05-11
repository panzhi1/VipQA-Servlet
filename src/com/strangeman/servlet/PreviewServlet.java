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


public class PreviewServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
private Gson gson;
private GsonBuilder builder;
private String jsonTest;
private QuestionPreview preview;


	public PreviewServlet(){
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
		            
		            preview=new QuestionPreview(60,"好用吗",5,"质量好吗",6);
		            builder=new GsonBuilder();
		            gson=builder.create();
		            
		            
		            jsonTest=gson.toJson(preview, QuestionPreview.class);
		
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
