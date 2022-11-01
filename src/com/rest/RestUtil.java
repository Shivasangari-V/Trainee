//$Id$
package com.rest;

import java.io.BufferedReader;

import org.json.JSONObject;

public class RestUtil {
	JSONObject res=null;
	Boolean db_sts=null; 
	JSONObject missing_data=null;
	public void setDBStatus(Boolean sts) {
		this.db_sts=sts;
	}
	public JSONObject getResponseStatus(){
		Boolean flag=null;
		JSONObject json_response=new JSONObject();
		if(this.missing_data!=null) {
			json_response.put("message:","MANDATORY_NOT_FOUND");
			json_response.put("details:",this.missing_data);
			flag=true;
		}else if(db_sts!=null){
			json_response.put("message","INTERNAL_SERVER_ERROR");
			flag=true;
		}else {
			json_response.put("message:","CODE_INSERTED_SUCCESSFULLY");
		}
		if(flag!=null) {
			json_response.put("status:","ERROR");
		}else {
			json_response.put("status:","SUCCESS");
		}
		return json_response;
		
	}
	public Boolean getFieldStatus(CRUDHandler crud,Model model)throws Exception{
		return null;
	}
	public JSONObject convertToJSON(BufferedReader reader) throws Exception {
		String line = null;
		StringBuilder JSONrequest = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			JSONrequest.append(line);
		}
		this.res = new JSONObject(JSONrequest.toString());
		return res;
	}


}
