//$Id$
package com.rest;

import java.sql.Statement;

import org.json.JSONObject;

public class DemoCRUDHandler {
	DBConnectors obj=new DBConnectors();
	Statement stmnt=null;
	public void insertCourse(JSONObject res) throws Exception{
		stmnt=(obj.getConnection()).createStatement();
		stmnt.executeUpdate("insert into Course values("+res.getString("id")+",'"+res.getString("name")+"')");
	}
	public void insertStudent(JSONObject json){
		
	}
	public void insertDepartment(JSONObject json) {
		
	}
	public void insertExam(JSONObject json) {
		
	}
	
}
