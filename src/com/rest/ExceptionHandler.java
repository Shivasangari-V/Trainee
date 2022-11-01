//$Id$
package com.rest;

import org.json.JSONObject;

public class ExceptionHandler extends Exception {
	private static final long serialVersionUID = 1L;
	JSONObject res=new JSONObject();
	public enum ErrorCode{
	INTERNAL_SERVER_ERROR,
	MANDATORY_NOT_FOUND,
	INVALID_DATA,
	ID_NOT_FOUND,
	LIMIT_EXCEEDED,
	SUCCESS,
	DATA_NOT_FOUND,
	DATA_EXISTS ,
	NOT_EDITABLE
	}public enum Status{
		SUCCESS,
		ERROR
	}
	public ExceptionHandler(ErrorCode code,String message,JSONObject fields,Status status,int statusCode) {
		res.put("code", code);
		res.put("message", message);
		res.put("details", fields);
		res.put("status", status);
		res.put("statusCode", statusCode);
	}
	public JSONObject getResponseJson(){
		return res;
	}

}
