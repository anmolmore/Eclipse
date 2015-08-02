package com.globom.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	/**
	 * Read request body for POST requests
	 * @param request
	 * @return
	 */
	public static String getRequestBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = request.getReader();
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append('\n');
				}
			} finally {
				reader.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
}
