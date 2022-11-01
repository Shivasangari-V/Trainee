//$Id$
package com.rest;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONObject;

public class StaffCRUDHandler implements CRUDHandler {
	@Override
	public JSONObject insertData(JSONObject request) throws Exception {
		JSONObject response = Validate.postValidatationConstraint("Staff", request);
		if (response.get("status") == ExceptionHandler.Status.SUCCESS) {
			ArrayList<String> constraint_fields = new ArrayList<String>();
			ArrayList<Object> constraint_values = new ArrayList<Object>();
			constraint_fields.add("id");
			constraint_values.add(request.getString("id"));
			if (DBUtil.isPresentData("Staff", constraint_fields, constraint_values)) {
				JSONObject details = new JSONObject();
				details.put("fields", constraint_fields);
				JSONObject dbUpdateResponse = new ExceptionHandler(ExceptionHandler.ErrorCode.DATA_EXISTS, "id already exists", details, ExceptionHandler.Status.ERROR, 422).getResponseJson();
				return dbUpdateResponse;
			} else {
				Model model = this.convertToPOJO(request);
				model = (Staff) model;
				ArrayList<String> fields = new ArrayList<String>();
				ArrayList<Object> values = new ArrayList<Object>();
				for (Field field : model.getClass().getDeclaredFields()) {
					fields.add(field.getName());
					field.setAccessible(true);
					values.add(field.get(model));
				}
				DBUtil.insertData("Staff", fields, values);
			}
		}

		return response;

	}

	@Override
	public JSONObject updateData(JSONObject request, JSONObject queryParam) throws Exception {
		JSONObject response = Validate.deleteValidation("Staff", queryParam);
		if (response.get("status") == ExceptionHandler.Status.SUCCESS) {
			ArrayList<String> constraint_keys = new ArrayList<String>();
			ArrayList<Object> constraint_values = new ArrayList<Object>();
			for (Object key : queryParam.keySet()) {
				constraint_keys.add(key.toString());
				constraint_values.add(queryParam.get(key.toString()));
			}
			if (DBUtil.isPresentData("Staff", constraint_keys, constraint_values) && request.length() != 0) {
				response = Validate.putValidation("Staff", request);
				if (response.get("status") == ExceptionHandler.Status.SUCCESS) {
					ArrayList<String> keys = new ArrayList<String>();
					ArrayList<Object> values = new ArrayList<Object>();
					for (Object key : request.keySet()) {
						keys.add(key.toString());
						values.add(request.get(key.toString()));
					}
					DBUtil.updateData("Staff", constraint_keys, constraint_values, keys, values);
				}
			} else {
				JSONObject details = new JSONObject();
				details.put("mismatch field", request);
				JSONObject dbUpdateResponse = new ExceptionHandler(ExceptionHandler.ErrorCode.DATA_NOT_FOUND, "field not found", details, ExceptionHandler.Status.ERROR, 206).getResponseJson();
				return dbUpdateResponse;
			}
		}
		return response;
	}

	@Override
	public JSONObject deleteData(JSONObject request) throws Exception {
		JSONObject response = Validate.deleteValidation("Staff", request);
		if (response.get("status") == ExceptionHandler.Status.SUCCESS) {
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			fields.add("id");
			values.add(request.get("id"));
			if (DBUtil.isPresentData("Staff", fields, values)) {
				DBUtil.deleteData("Staff", request.getString("id").toString());
			} else {
				JSONObject details = new JSONObject();
				details.put("mismatch field", request);
				JSONObject dbUpdateResponse = new ExceptionHandler(ExceptionHandler.ErrorCode.DATA_NOT_FOUND, "field not found in the table", details, ExceptionHandler.Status.ERROR, 206).getResponseJson();
				return dbUpdateResponse;
			}
		}
		return response;
	}

	@Override
	public JSONObject getData(JSONObject request) throws Exception {
		JSONObject response = Validate.deleteValidation("Staff", request);
		if (response.get("status") == ExceptionHandler.Status.SUCCESS) {
			ArrayList<String> constraint_keys = new ArrayList<String>();
			ArrayList<Object> constraint_values = new ArrayList<Object>();
			constraint_values.add(request.get("id"));
			constraint_keys.add("id");
			if (DBUtil.isPresentData("Staff", constraint_keys, constraint_values)) {
				Model model = this.convertToPOJO(request);
				model = (Staff) model;
				ArrayList<String> getFields = new ArrayList<String>();
				for (Field field : model.getClass().getDeclaredFields()) {
					getFields.add(field.getName());
				}
				JSONObject DBStatusResponse = DBUtil.getData("Staff", "id", request.get("id"), getFields);
				DBStatusResponse.put("statusCode", 200);
				return DBStatusResponse;
			} else {
				JSONObject details = new JSONObject();
				details.put("mismatch field", constraint_keys);
				JSONObject dbUpdateResponse = new ExceptionHandler(ExceptionHandler.ErrorCode.DATA_NOT_FOUND, "constraint field value not found", details, ExceptionHandler.Status.ERROR, 206).getResponseJson();
				return dbUpdateResponse;
			}

		}
		return response;
	}

	@Override
	public Model convertToPOJO(JSONObject request) {
		Staff staff =new Staff();
		staff.setId(request.optString("id",null));
		staff.setName(request.optString("name",null));
		staff.setAge(request.optString("age",null));
		staff.setDoj(request.optString("doj",null));
		staff.setPhone(request.optString("phone",null));
		staff.setReport_to(request.optString("report_to",null));
		return staff;
		
		
	}

}
