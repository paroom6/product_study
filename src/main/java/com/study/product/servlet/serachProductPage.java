package com.study.product.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.service.ProductService;

@WebServlet("/Product.do")
public class serachProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService;
    public serachProductPage() {
        super();
        productService = ProductService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("productList", productService.searchProduct());
		request.getRequestDispatcher("WEB-INF/views/searh.jsp").forward(request, response);

	}


}
