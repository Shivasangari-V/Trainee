//$Id$
package com.myaction;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class Student {
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public void myaction() throws IOException {
		
		JSONArray myarray = new JSONArray();
		myarray.put(new JSONObject().put("Name","Siva sangari"));
		myarray.put(new JSONObject().put("ID","PT-6041"));
		myarray.put(new JSONObject().put("Team","CRM APIManagement"));
		
		PrintWriter out = response.getWriter();
		out.write(myarray.toString());
	}

}
