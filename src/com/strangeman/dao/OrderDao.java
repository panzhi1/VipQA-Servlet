package com.strangeman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.strangeman.database.DBUtils;
import com.strangeman.entity.Order;

public class OrderDao {
	private DBUtils dbUtils;
	private Connection connection;
	
	public OrderDao(Connection connection){
		this.connection=connection;
		dbUtils=DBUtils.getDBUtils();
	}
	
	public Order getOrderDetail(String orderId)throws SQLException{
		return null;
	}
	
	public List<Order> getOrders(String userId)throws SQLException{
		String sql="select * from orders where userid = ? order by orderdate desc";
		List<Order> orders=null;
		Order order=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				if(orders==null)
					orders=new ArrayList<>();
				String orderId=resultSet.getString("orderId");
				String productId=resultSet.getString("productId");
				String orderDate=resultSet.getString("orderDate");
				int count=resultSet.getInt("count");
				
				order=new Order(orderId, userId, productId, orderDate, count);
				orders.add(order);
			}
		} finally {
			dbUtils.closeStatement(preparedStatement);
		}
		return orders;
	}
}
