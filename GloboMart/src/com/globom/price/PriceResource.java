package com.globom.price;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.globom.annotation.HttpRequestMethod;
import com.globom.annotation.HttpRequestPath;
import com.globom.annotation.HttpRequestType;
import com.globom.derby.DatabaseImpl;

@HttpRequestPath(path = "/price")
public class PriceResource {
	
	@HttpRequestMethod(requestType = HttpRequestType.GET, path="/{id}")
	public void getPriceById(HttpServletRequest request, HttpServletResponse response) {
		String requestUri = request.getRequestURI();
		Long id = Long.valueOf(requestUri.substring(requestUri.indexOf("/price/") + 7));
		DatabaseImpl databaseImpl = new DatabaseImpl();
		Double price = databaseImpl.getPriceById(id);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"price\":");
		sb.append(price);
		sb.append("}");
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
