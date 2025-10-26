package com.jayesh.swingdemo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectivity {

	Driver driver;
	Connection connection;
	Statement stmt;
	
	public void getConnection() {
		try {
			
			driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingdemodb","root","Jayesh");
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			
		} catch (Exception ex) {
			
			System.out.println("Error in Connection "+ex);
			
		}
	}
	public void insertData(int id, String name, String email, String contact) {
		
		try {
			stmt.executeUpdate("insert into contactinfo values("+id+",'"+name+"','"+email+"','"+contact+"')");
		
		} catch (SQLException e) {
			
			System.out.println("Error in Inserting Record "+e);
		}
	}
	
	
	public void updateData(int id, String name, String email, String contact){
		
		try {
			
			stmt.executeUpdate("update contactinfo set name='"+name+"',email='"+email+"',contact='"+contact+"'where id="+id);
			System.out.println("Record Updated Successfully!!");
		} catch (SQLException e) {
			
			System.out.println("Error in Update recors"+e);
		}
	}; 
	
	public void deleteData(int id) {
		
		try {
			stmt.executeUpdate("delete from contactinfo where id="+id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void closeConnection() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
	}
}
