//$Id$
package com.rest;

//@TableName("Courses")
public class Course  extends Model{
	private String id,name;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}