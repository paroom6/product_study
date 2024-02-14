package com.study.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.study.product.config.DBConfig;
import com.study.product.entity.Product;

public class ProductDao {
	private static ProductDao instance;
	Connection con = null;
	PreparedStatement pstmt = null;
	int successCount = 0;
	ResultSet rs = null;
	private ProductDao() {

	}
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;		
	}
	public int addProduct(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.URL,DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into product_tb(product_name, product_price, product_size) values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,product.getProduct_name());
			pstmt.setInt(2,product.getProduct_price());
			pstmt.setString(3,product.getProduct_size().toUpperCase());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return successCount;
		
	}
	public List<Product> searchProduct() {
		List<Product> products = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.URL,DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = Product.builder()
										.product_id(rs.getInt(1))
										.product_name(rs.getString(2))
										.product_price(rs.getInt(3))
										.product_size(rs.getString(4))
										.build();
				products.add(product);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();
					}
					if(rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		
		return products;
	}

}
