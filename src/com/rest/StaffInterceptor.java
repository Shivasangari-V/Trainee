//$Id$
package com.rest;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class StaffInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		System.out.println("Staff Interceptors Destroyed...");
		
	}

	@Override
	public void init() {
		System.out.println("Staff Interceptor Initialised...");
		
	}

	@Override
	public String intercept(ActionInvocation arg) throws Exception {
		return arg.invoke();
	}

}
