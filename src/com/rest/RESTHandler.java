//$Id$
package com.rest;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class RESTHandler {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public static int count = 0;

	public JSONObject getQueryParam() {
		JSONObject response = new JSONObject();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String parameterName = (String) params.nextElement();
			response.put(parameterName, request.getParameter(parameterName));
		}
		return response;
	}

	public void getToken() throws Exception {
		response.setContentType("application/json");
		JSONObject queryParam = this.getQueryParam();
		String token = ClientTokenGenrator.tokenGenerator(queryParam.get("userID").toString());
		JSONObject response_json = new JSONObject();
		PrintWriter out = response.getWriter();
		response_json.put("Token", token);
		out.write(response_json.toString());
	}

	public void postDepartment() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new DepartmentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void deleteDepartment() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new DepartmentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void putDepartment() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new DepartmentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getDepartment() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new DepartmentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void postCourse() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new CourseCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void deleteCourse() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new CourseCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void putCourse() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new CourseCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getCourse() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new CourseCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void postCourseDetails() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new CourseDetailsCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void deleteCourseDetails() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new CourseDetailsCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void putCourseDetails() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new CourseDetailsCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getCourseDetails() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new CourseDetailsCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void postExam() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new ExamCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void deleteExam() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new ExamCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void putExam() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new ExamCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getExam() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new ExamCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();

		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void postStaff() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new StaffCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void deleteStaff() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new StaffCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void putStaff() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new StaffCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getStaff() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new StaffCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void postStudent() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new StudentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_json = Util.convertToJson(reader);
		JSONObject response_json = crud.insertData(request_json);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void deleteStudent() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new StudentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.deleteData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());

	}

	public void putStudent() throws Exception {
		BufferedReader reader = request.getReader();
		response.setContentType("application/json");
		CRUDHandler crud = new StudentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject request_body = Util.convertToJson(reader);
		JSONObject queryParam = this.getQueryParam();
		JSONObject response_json = crud.updateData(request_body, queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}

	public void getStudent() throws Exception {
		response.setContentType("application/json");
		CRUDHandler crud = new StudentCRUDHandler();
		PrintWriter out = response.getWriter();
		JSONObject queryParam = this.getQueryParam();
//		Thread.sleep(10000);
		System.out.println("API Call count: " + count++);
		JSONObject response_json = crud.getData(queryParam);
		response.setStatus(response_json.getInt("statusCode"));
		response_json.remove("statusCode");
		out.write(response_json.toString());
	}
}
