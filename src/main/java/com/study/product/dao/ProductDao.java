package com.study.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.study.product.config.DBConfig;
import com.study.product.dto.insertProductReqDto;
import com.study.product.vo.ProductVo;

public class ProductDao {//data access object
	private static ProductDao instance;
	private ProductDao() {

	}
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;		
	}
	public int saveProduct(ProductVo productVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.URL,DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into product_tb values (0,?,?,?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,productVo.getProduct_name());
			pstmt.setInt(2,productVo.getProduct_price());
			pstmt.setString(3,productVo.getProduct_size().toUpperCase());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				productVo.setProduct_id(rs.getInt(1));
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
		return successCount;
		
	}
	
	public ProductVo findProductByProductName(String productName) {
		ProductVo productVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.URL,DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from product_tb where product_name = ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				productVo = ProductVo.builder()
						.product_id(rs.getInt(1))
						.product_name(rs.getString(2))
						.product_price(rs.getInt(3))
						.product_size(rs.getString(4))
						.build();
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
		
		return productVo;
	}
	
	public List<ProductVo> searchProduct() {
		List<ProductVo> products = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.URL,DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);   
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo productVo = ProductVo.builder()
										.product_id(rs.getInt(1))
										.product_name(rs.getString(2))
										.product_price(rs.getInt(3))
										.product_size(rs.getString(4))
										.build();
				products.add(productVo);
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
