//$Id$
package com.rest;

import java.io.File;

public class ServiceUtil {
	public static String getFilePath(String fileName)throws Exception{
		File file=new File(fileName);
		String path=file.getAbsolutePath();
		path= path.replace("bin", "webapps/strutpro");
		System.out.println("Path ::::: " + path);
		return path;
	}
	public static void main(String args[]) throws Exception {
//		System.out.println(convertFileToString("Validation.json"));
	}
}
