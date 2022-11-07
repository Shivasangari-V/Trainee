//$Id$
package com.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class ErrorResponseHandler extends Throwable {
	HttpServletResponse response=ServletActionContext.getResponse();
	PrintWriter out=null;
	private static final long serialVersionUID = 1L;
	public ErrorResponseHandler(String msg) throws IOException {
		super(msg);
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out =response.getWriter();
		out.write(msg.toString());
		
	}
	public static void throwErrorResponse(JSONObject msg) throws Exception {
		
	}
}
