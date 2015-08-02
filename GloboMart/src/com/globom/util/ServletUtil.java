package com.globom.util;


public class ServletUtil {
	
	/**
	 * Matches upcoming requests
	 * TODO Better matcher utility can be written to properly match requests
	 * @param resourcePath
	 * @param requestPath
	 * @return returns true if request is honored by this server else false
	 */
	public static boolean matcher(String resourcePath, String requestPath) {
		if(resourcePath.equals("/products/{id}")) {
			if(requestPath.startsWith("/products/"))
				return true;
		}
		//match price GET service
		if(resourcePath.equals("/price/{id}")) {
			if(requestPath.startsWith("/price/"))
				return true;
		}
		return false;
	}
}
