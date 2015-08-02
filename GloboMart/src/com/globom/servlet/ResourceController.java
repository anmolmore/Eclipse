package com.globom.servlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.globom.annotation.HttpRequestMethod;
import com.globom.annotation.HttpRequestPath;
import com.globom.annotation.HttpRequestType;
import com.globom.price.PriceResource;
import com.globom.product.ProductsResource;

public class ResourceController {

	Set<Class> classes = null;
	List<ResourceMapper> resourceList;

	public ResourceController() {
		classes = new HashSet<Class>();
		resourceList = new ArrayList<ResourceMapper>();
	}

	/**
	 * load all resource classes having entry points for rest requests
	 * 
	 * @return List of all available resurce processing in mapped structure
	 */
	public List<ResourceMapper> loadResources() {
		// load all resource classes supported in this application
		classes.add(ProductsResource.class);
		classes.add(PriceResource.class);
		loadAnnotations();
		return resourceList;
	}

	/**
	 * Load annotations associated with each class and fill resourceMap
	 */
	private void loadAnnotations() {
		for (Class clazz : classes) {
			HttpRequestPath httpRequestPath = (HttpRequestPath) clazz.getAnnotation(HttpRequestPath.class);
			String basePath = httpRequestPath.path();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				HttpRequestMethod httpRequestMethod = method
						.getAnnotation(HttpRequestMethod.class);
				String methodPath = httpRequestMethod.path();
				HttpRequestType requestType = httpRequestMethod.requestType();
				String path = basePath + methodPath;
				ResourceMapper mapper = new ResourceMapper(path, clazz, method,
						requestType);
				resourceList.add(mapper);
			}
		}
	}
}
