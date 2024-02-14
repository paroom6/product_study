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
import com.study.product.entity.Product;


@WebServlet("/product/list")
public class SelectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao dao = ProductDao.getInstance();
		Gson gson = new Gson();
		List<Product> products = dao.searchProduct();
		Map<String, Object> map = new HashMap();
		map.put("data", products);
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(map));
	}



}
