package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.service.ProductService;
import com.study.product.utils.ResponseEntity;
import com.study.product.vo.ProductVo;


@WebServlet("/product/list")
public class SelectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
    public SelectProductServlet() {
        super();
        productService = ProductService.getInstance();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseEntity.ofJson(response, productService.searchProduct(), 200);
	}



}
