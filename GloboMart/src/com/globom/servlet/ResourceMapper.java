package com.globom.servlet;

import java.lang.reflect.Method;

import com.globom.annotation.HttpRequestType;

public class ResourceMapper {
	private String path;
	private Class clazz;
	private Method method;
	private HttpRequestType httpRequestType;

	public ResourceMapper(String path, Class clazz, Method method,
			HttpRequestType httpRequestType) {
		super();
		this.path = path;
		this.clazz = clazz;
		this.method = method;
		this.httpRequestType = httpRequestType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public HttpRequestType getHttpRequestType() {
		return httpRequestType;
	}

	public void setHttpRequestType(HttpRequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}
}
