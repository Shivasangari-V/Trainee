//$Id$
package com.rest;

import java.io.BufferedReader;

import org.json.JSONObject;

public class Util {
	public static JSONObject convertToJson(BufferedReader reader)throws Exception {
		String line=null;
		StringBuilder JSONRequest=new StringBuilder();
		while((line=reader.readLine())!=null) {
			JSONRequest.append(line);
		}
		JSONObject res=new JSONObject(JSONRequest.toString());
		return res;
	}

}
