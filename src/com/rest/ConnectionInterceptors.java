//$Id$
package com.rest;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ConnectionInterceptors implements Interceptor {

	private static final long serialVersionUID = 1L;
	Scanner sc=new Scanner(System.in);

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {		
		System.out.println("DBInterceptor connnected,..,.....");
		FileReader reader=null;
		try {
			
			reader = new FileReader(ServiceUtil.getFilePath("db.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties prop=new Properties();
		try {
			prop.load(reader);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String user=prop.getProperty("user");
		String password=prop.getProperty("password");
		String databaseName=prop.getProperty("databaseName");
		
		try {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String mysqlUrl = "jdbc:mysql://localhost:3306";
		Connection con = DriverManager.getConnection(mysqlUrl,user,password);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create database "+databaseName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("here the Db details:"+user+" "+password+" "+databaseName);
		new DBConnectors(user,password,databaseName);
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("Connection geted...");
		return arg0.invoke();
	}

}
