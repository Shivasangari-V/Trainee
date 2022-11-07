<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<% String error = exception.getMessage();
response.setContentType("application/json");%>
<%=error%>
