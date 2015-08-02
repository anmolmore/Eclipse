package com.globom.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.globom.annotation.HttpRequestMethod;
import com.globom.annotation.HttpRequestPath;
import com.globom.annotation.HttpRequestType;
import com.globom.derby.DatabaseImpl;
import com.globom.util.StringUtil;

@HttpRequestPath(path = "/products")
public class ProductsResource {

	/**
	 * Get List of all products in products table and send as response
	 * 
	 * @param response
	 */
	@HttpRequestMethod(requestType = HttpRequestType.GET)
	public void getProducts(HttpServletRequest request, HttpServletResponse response) {
		DatabaseImpl databaseImpl = new DatabaseImpl();
		List<Product> products = databaseImpl.getAllProducts();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"products\":[");
		int i = 0;
		while (i < products.size()) {
			Product product = products.get(i);
			sb.append(product.toJson());
			i++;
			if (i < products.size()) {
				sb.append(",");
			}
		}
		sb.append("]}");
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	/**
	 * Adds a product to DB, request received as Json
	 * 
	 * @param request
	 * @param response
	 */
	@HttpRequestMethod(requestType = HttpRequestType.POST)
	public void addProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String requestBody = StringUtil.getRequestBody(request);
		JSONObject requestJson = new JSONObject(requestBody);
		Product product = new Product();
		product.setName(requestJson.getString("name"));
		product.setDescription(requestJson.getString("description"));
		Long categoryId = requestJson.getLong("categoryId");
		if (categoryId != null)
			product.setCategoryId(categoryId);
		else
			product.setCategoryId(new Long(0));
		product.setUom(requestJson.getString("uom"));
		DatabaseImpl databaseImpl = new DatabaseImpl();
		boolean insertFlag = databaseImpl.addProduct(product);
		if (insertFlag) {
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * delete a product from db for requested id
	 * 
	 * @param request
	 * @param response
	 */
	@HttpRequestMethod(requestType = HttpRequestType.DELETE, path="/{id}")
	public void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String requestUri = request.getRequestURI();
		Long id = Long.valueOf(requestUri.substring(requestUri.indexOf("/products/") + 10));
		DatabaseImpl databaseImpl = new DatabaseImpl();
		boolean insertFlag = databaseImpl.deleteProduct(id);
		if (insertFlag) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * TODO Fix this method to query on product name
	 * @param response
	 */
	@HttpRequestMethod(requestType = HttpRequestType.GET, path = "?name=")
	public void searchProductByName(HttpServletRequest request, HttpServletResponse response) {
		// TODO fix this logic
		String requestUri = request.getRequestURI();
		//get product name
		DatabaseImpl databaseImpl = new DatabaseImpl();
		//Product product = databaseImpl.getProductByName(name)
		//Fix this
		StringBuilder sb = new StringBuilder();
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			response.setStatus(500);
			e.printStackTrace();
		}
	}
}