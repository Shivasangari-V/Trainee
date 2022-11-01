//$Id$
package com.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DBConnectors { 
	static Connection con=null;
	public static String currentDirectory = System.getProperty("user.dir");
  	public DBConnectors() {
  	}
	public DBConnectors(String user,String password,String databaseName) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connection = "jdbc:mysql://localhost:3306/"+databaseName;
		    con = (Connection) DriverManager.getConnection(connection,user,password);
		    Class.forName("org.apache.ibatis.jdbc.ScriptRunner");
		    ScriptRunner sr = new ScriptRunner(con);
		    Reader reader = new BufferedReader(new FileReader(ServiceUtil.getFilePath("Demo.sql")));
			sr.runScript(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws Exception {
	    return con;
	}
	
}
