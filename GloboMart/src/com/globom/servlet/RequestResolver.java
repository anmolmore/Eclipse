package com.globom.servlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globom.util.ServletUtil;

public class RequestResolver {

	public static void resolve(HttpServletRequest request,
			HttpServletResponse response) {
		String requestUrl = request.getRequestURI();
		if (request.getQueryString() != null) {
			requestUrl += "?" + request.getQueryString();
		}
		String requestMethod = request.getMethod();
		ResourceController controller = new ResourceController();
		List<ResourceMapper> resourceList = controller.loadResources();
		callMatchingMethod(requestUrl, requestMethod, resourceList, request, response);
	}

	/**
	 * Call relevant method by reflection
	 * 
	 * @param requestUri
	 *            uri of request
	 * @param requestMethod
	 *            request of type GET, POST, PUT, DELETE
	 * @param resourceMap
	 *            List of all available processing in this application
	 */
	private static void callMatchingMethod(String requestUri,
			String requestMethod, List<ResourceMapper> resourceList, HttpServletRequest request,
			HttpServletResponse response) {
		for (ResourceMapper resource : resourceList) {
			String entityName = requestUri.substring(requestUri
					.indexOf("/rest") + 5);
			if (resource.getPath().equals(entityName) || ServletUtil.matcher(resource.getPath(), entityName)) {
				Class clazz = resource.getClazz();
				Method method = resource.getMethod();
				if (resource.getHttpRequestType().name().equals(requestMethod)) {
					Object myClass;
					try {
						myClass = clazz.newInstance();
						method.invoke(myClass, new Object[] { request, response });
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
