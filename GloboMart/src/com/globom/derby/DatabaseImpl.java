package com.globom.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.globom.product.Product;

public class DatabaseImpl {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	/**
	 * select * from products
	 * 
	 * @return List of all products from database
	 */
	public List<Product> getAllProducts() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connect = DriverManager
					.getConnection("jdbc:derby://localhost:1527//MyDB;create=true");
			PreparedStatement statement = connect
					.prepareStatement("SELECT * from GLOBOMART.PRODUCTS");
			resultSet = statement.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (resultSet.next()) {
				Long id = resultSet.getLong("ID");
				String name = resultSet.getString("NAME");
				String description = resultSet.getString("DESCRIPTION");
				Long categoryId = resultSet.getLong("CATEGORYID");
				String uom = resultSet.getString("UOM");
				Product product = new Product(id, name, description,
						categoryId, uom);
				products.add(product);
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	/**
	 * Add a product to DB PRODUCTS table
	 * 
	 * @param product
	 * @return true if create succeeded
	 */
	public boolean addProduct(Product product) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connect = DriverManager
					.getConnection("jdbc:derby://localhost:1527//MyDB;create=true");
			String insertProduct = "INSERT INTO GLOBOMART.PRODUCTS"
					+ "(NAME, DESCRIPTION, CATEGORYID, UOM) VALUES"
					+ "(?,?,?,?)";
			PreparedStatement preparedStatement = connect
					.prepareStatement(insertProduct);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setLong(3, product.getCategoryId());
			preparedStatement.setString(4, product.getUom());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
		//TODO Fix to return true only if statement succeseds
		return true;
	}
	
	/**
	 * delete a product from DB PRODUCTS table
	 * 
	 * @param product
	 * @return true if create succeeded
	 */
	public boolean deleteProduct(Long id) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connect = DriverManager
					.getConnection("jdbc:derby://localhost:1527//MyDB;create=true");
			String deleteSQL = "DELETE FROM GLOBOMART.PRODUCTS WHERE ID = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(deleteSQL);
			preparedStatement.setLong(1, id);
			// execute delete SQL stetement
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
		//TODO Fix to return true only if statement succeseds
		return true;
	}
	
	/**
	 * Get Price of a product with given id
	 * @return
	 */
	public Double getPriceById(Long id) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connect = DriverManager
					.getConnection("jdbc:derby://localhost:1527//MyDB;create=true");
			PreparedStatement statement = connect
					.prepareStatement("SELECT PRICE from GLOBOMART.PRICE WHERE PRODUCTID=?" );
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Double price = resultSet.getDouble("PRICE");
				return price;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return (double) 0;
	}

	/**
	 * Close db connection
	 */
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

//	 public static void main(String[] args) {
//		 //Product product = new Product(Long.valueOf(1), "Colgate",
//		 //null,Long.valueOf(0), null);
//		 DatabaseImpl db = new DatabaseImpl();
//		 //db.deleteProduct(Long.valueOf(1001));
//		 System.out.println(db.getPriceById(Long.valueOf(1004)));
//	 }
}
