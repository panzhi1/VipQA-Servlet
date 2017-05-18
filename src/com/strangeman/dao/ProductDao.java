package com.strangeman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.strangeman.database.DBUtils;
import com.strangeman.entity.Product;

public class ProductDao {
	private DBUtils dbUtils;
	private Connection connection;
	
	public ProductDao(Connection connection){
		this.connection=connection;
		dbUtils=DBUtils.getDBUtils();
	}
	
	public String addProduct(String productName, float price, String image) throws SQLException{
		return null;
	}
	public void deleteProduct(String productId) throws SQLException{
		
	}
	public void updateProduct(Product product) throws SQLException{
		
	}
	
	public Product getAProduct(String productId) throws SQLException{
		String sql="select * from product where productid = ?";
		Product product=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, productId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				String productName=resultSet.getString("productName");
				float price=resultSet.getFloat("price");
				String imagePath=resultSet.getString("imagePath");
				
				product=new Product(productId, productName, price, imagePath);
			}
		}finally{
			dbUtils.closeStatement(preparedStatement);
		}
		return product;
	}
	public List<Product> getAllProduct() throws SQLException{
		String sql="select * from product";
		List<Product> products=null;
		Product product=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				if(products==null)
					products=new ArrayList<>();
				String productId=resultSet.getString("productId");
				String productName=resultSet.getString("productName");
				String imagePath=resultSet.getString("imagePath");
				float price=resultSet.getFloat("price");
				
				product=new Product(productId, productName, price, imagePath);
				products.add(product);
			}
		} finally {
			dbUtils.closeStatement(preparedStatement);
		}
		return products;
	}
}
