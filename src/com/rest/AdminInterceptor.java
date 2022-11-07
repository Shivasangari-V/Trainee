//$Id$
package com.rest;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AdminInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1415355949912268615L;

	@Override
	public void destroy() {
		System.out.println("Admin Interceptor Destroyed....");

	}

	@Override
	public void init() {
		System.out.println("Admin Interceptor Initialised....");

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject response_json=null;
		Boolean flag=true;
		String requestURI[]=request.getRequestURI().split("/");
		int len=requestURI.length;
		if(requestURI[len-1].equalsIgnoreCase("token")){
			String userID=request.getParameter("userID");
			String password=request.getParameter("password");
			ArrayList<String> constraint_keys=new ArrayList<String>();
			ArrayList<String> constraint_values = new ArrayList<String>();
			constraint_keys.add("clientID");
			constraint_keys.add("password");
			constraint_values.add(userID);
			constraint_values.add(password);
			Boolean userAccess=DBUtil.isPresentData("login",constraint_keys,constraint_values);
			if(!userAccess) {
				JSONObject details=new JSONObject();
				details.put("user", userID);
				response_json= new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "user not found", details, ExceptionHandler.Status.ERROR,206).getResponseJson();
				response.setStatus(response_json.getInt("statusCode"));
				response_json.remove("statusCode");
				throw new RuntimeException(response_json.toString());
				
			}else {
				return arg0.invoke();
			}
		}else{
			String token = request.getHeader("Authorization");
			ArrayList<String> constraint_keys=new ArrayList<String>();
			ArrayList<String> constraint_values = new ArrayList<String>();
			constraint_keys.add("token");
			constraint_values.add(token);
			Boolean isValid=DBUtil.isPresentData("ClientTokenMapper", constraint_keys, constraint_values);
			
			if(!isValid) {
				JSONObject details=new JSONObject();
				details.put("token",token);
				response_json= new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "invalid token", details, ExceptionHandler.Status.ERROR,400).getResponseJson();
				response.setStatus(response_json.getInt("statusCode"));
				response_json.remove("statusCode");
				throw new RuntimeException(response_json.toString());
			}
			try{
			if(!ClientConnectionMapper.isAllowed(token)) {
				JSONObject details=new JSONObject();
				details.put("token",token);
				response_json= new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "token Expired", details, ExceptionHandler.Status.ERROR,400).getResponseJson();
				response.setStatus(response_json.getInt("statusCode"));
				response_json.remove("statusCode");
				throw new RuntimeException(response_json.toString());
			}else {
				return arg0.invoke();
			}
			}
		finally {
			ClientConnectionMapper.workDoneEntry(token);
		}
		}
}
}
