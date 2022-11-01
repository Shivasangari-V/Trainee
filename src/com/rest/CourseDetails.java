//$Id$
package com.rest;

public class CourseDetails extends Model {
 private String StdentID,CourseID,StaffID;

public String getStdentID() {
	return StdentID;
}

public void setStdentID(String stdentID) {
	StdentID = stdentID;
}

public String getCourseID() {
	return CourseID;
}

public void setCourseID(String courseID) {
	CourseID = courseID;
}

public String getStaffID() {
	return StaffID;
}

public void setStaffID(String staffID) {
	StaffID = staffID;
}
 
}
