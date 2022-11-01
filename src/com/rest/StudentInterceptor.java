//$Id$
package com.rest;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class StudentInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		System.out.println("Student Inetrceptor Destoyed....");		
	}

	@Override
	public void init() {
		System.out.println("Student Interceptor Initialised...");
	}

	@Override
	public String intercept(ActionInvocation arg) throws Exception {
		return arg.invoke();
	}

}
