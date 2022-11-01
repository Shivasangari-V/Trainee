//$Id$
package com.rest;

import org.json.JSONObject;

public interface CRUDHandler 
{
	public JSONObject  insertData(JSONObject request_json)throws Exception;
	public JSONObject  updateData(JSONObject request_json,JSONObject query_param) throws Exception;
	public JSONObject  deleteData(JSONObject request_json) throws Exception;
	public JSONObject  getData(JSONObject request_json)throws Exception;
	public Model  convertToPOJO(JSONObject request_json);
}
