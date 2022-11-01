//$Id$
package com.rest;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AdminInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1415355949912268615L;

	@Override
	public void destroy() {
		System.out.println("Admin Interceptor Destroyed....");

	}

	@Override
	public void init() {
		System.out.println("Admin Interceptor Initialised....");

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		return arg0.invoke();
	}

}
