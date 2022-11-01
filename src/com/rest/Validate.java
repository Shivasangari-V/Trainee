//$Id$
package com.rest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class Validate 
{
	static JSONObject validatationScheme = null;
	
	public Validate() {
		try {
			validatationScheme = new JSONObject(new String(Files.readAllBytes(Paths.get(ServiceUtil.getFilePath("ValidationScheme.json")))));
		} catch (Exception e) {
			System.out.println("file not found...");
		}
	}

	public static JSONObject validator(Object value, JSONObject fieldList, String key) {
		JSONObject details = new JSONObject();
		JSONObject tableField = (JSONObject) fieldList.get(key);
		String dataType = tableField.optString("type", null);
		if (dataType.equals("Text") && (value != null && !(value instanceof String))) {
			details.put("field", key);
			details.put("data_type", tableField.opt("type"));
			return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "data type mismatch", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
		}
		if (dataType.equals("Number") && value != null && !(value instanceof Integer)) {
			details.put("field", key);
			details.put("data_type", tableField.opt("type"));
			return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "data type mismatch", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
		}
		if (dataType.equals("Date") && value != null) {
			Pattern date = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$");
			Matcher match = date.matcher(value.toString());
			Boolean flag = true;
			while (match.find()) {
				flag = false;
			}
			if (flag) {
				details.put("field", key);
				details.put("data_type", tableField.opt("type"));
				details.put("format", "DD-MM-YYYY");
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "data type mismatch", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
			}
		} else {
			int minLength = tableField.getInt("min-length");
			int maxLength = tableField.getInt("max-length");
			if (value != null && (value instanceof String) && (value.toString()).length() > maxLength) {
				details.put("field", key);
				details.put("max-length", maxLength);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "value is too long ", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
			}
			if (value != null && (value instanceof Integer) && ((int) value) > maxLength) {
				details.put("field", key);
				details.put("max-length", maxLength);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "value is too long ", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
			}

			if (value != null && (value instanceof String) && (value.toString()).length() < minLength) {
				details.put("field", key);
				details.put("min-length", minLength);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "value is too short ", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
			}
			if (value != null && (value instanceof Integer) && ((int) value) < minLength) {
				details.put("field", key);
				details.put("min-length", minLength);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.INVALID_DATA, "value is too short ", details, ExceptionHandler.Status.ERROR, 400)).getResponseJson();
			}
		}
		return null;

	}

	public static JSONObject postValidatationConstraint(String tableName, JSONObject request) {
		JSONObject fieldList = validatationScheme.getJSONObject(tableName);
		JSONObject details = new JSONObject();
		for (Object keys : fieldList.keySet()) {
			String key = keys.toString();
			Object requestField = request.optString(key, null);
			JSONObject tableField = (JSONObject) fieldList.get(key);
			boolean required = tableField.getBoolean("required");
			if (required && requestField == null) {
				System.out.println(required + "this is given " + requestField + key.toString());
				details.put("field", key);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.MANDATORY_NOT_FOUND, "mandatory field not found", details, ExceptionHandler.Status.ERROR, 206)).getResponseJson();
			}
			JSONObject response = validator(request.optString(key, null), fieldList, key);
			if (response != null) {
				return response;
			}
		}
		details.put("fields", request);
		return new ExceptionHandler(ExceptionHandler.ErrorCode.SUCCESS, "inserted successfully", details, ExceptionHandler.Status.SUCCESS, 201).getResponseJson();
	}

	public static JSONObject deleteValidation(String tableName, JSONObject request) {
		JSONObject fieldList = validatationScheme.getJSONObject(tableName);
		JSONObject details = new JSONObject();
		Object requestField = request.optString("id", null);
		JSONObject tableField = (JSONObject) fieldList.get("id");
		boolean required = tableField.getBoolean("required");
		if (required && requestField == null) {
			details.put("field", "id");
			return (new ExceptionHandler(ExceptionHandler.ErrorCode.MANDATORY_NOT_FOUND, "mandatory field not found", details, ExceptionHandler.Status.ERROR, 206)).getResponseJson();
		}
		JSONObject response = validator(request.optString("id", null), fieldList, "id");
		if (response != null) {
			return response;
		}
		details.put("id", request.opt("id"));
		return new ExceptionHandler(ExceptionHandler.ErrorCode.SUCCESS, "deleted succesfully", details, ExceptionHandler.Status.SUCCESS, 200).getResponseJson();
	}

	public static JSONObject putValidation(String tableName, JSONObject request) {
		JSONObject fieldList = validatationScheme.getJSONObject(tableName);
		JSONObject details = new JSONObject();
		for (Object key : request.keySet()) {
			if (fieldList.optString((key.toString()), null) == null) {
				details.put("field", key);
				return (new ExceptionHandler(ExceptionHandler.ErrorCode.DATA_NOT_FOUND, "given data not found in the table", details, ExceptionHandler.Status.ERROR, 206)).getResponseJson();
			} else {
				JSONObject tableField = (JSONObject) fieldList.get(key.toString());
				boolean editable = tableField.getBoolean("editable");
				if (!editable) {
					details.put("fields", key);
					return (new ExceptionHandler(ExceptionHandler.ErrorCode.NOT_EDITABLE, "given field not editable", details, ExceptionHandler.Status.ERROR, 304)).getResponseJson();

				}

				JSONObject response = validator(request.optString(key.toString(), null), fieldList, key.toString());
				if (response != null)
					return response;
			}
		}
		details.put("field", request);
		return (new ExceptionHandler(ExceptionHandler.ErrorCode.SUCCESS, "table updated", details, ExceptionHandler.Status.SUCCESS, 200)).getResponseJson();

	}
}
