//$Id$
package com.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.mapper.DefaultActionMapper;

import com.opensymphony.xwork2.config.ConfigurationManager;

public class UserMap implements ActionMapper{

	@Override
	public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
		ActionMapping map=new DefaultActionMapper().getMapping(request,configManager);
		String name=map.getName();
		String methodName=""+((char)((name.charAt(0))-32))+name.substring(1);
		map.setMethod((request.getMethod()).toLowerCase()+methodName);
		return map;
	}

	@Override
	public ActionMapping getMappingFromActionName(String actionName) {
		return null;
	}

	@Override
	public String getUriFromActionMapping(ActionMapping mapping) {
		return null;
	}
	

}
