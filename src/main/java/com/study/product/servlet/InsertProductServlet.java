package com.study.product.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.dto.insertProductPespDto;
import com.study.product.dto.insertProductReqDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;
import com.study.product.vo.ProductVo;


@WebServlet("/product/addition")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       

    public InsertProductServlet() {
        super();
        productService = ProductService.getInstance();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertProductReqDto reqDto = insertProductReqDto.builder().build(); //받는 데이터에 맞게 설계
//		Product product = Product.builder().build();
		try {
			reqDto = RequestUtil.convertJsonData(request, insertProductReqDto.class);		
		} catch ( Exception e) {
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMasseage","정보를 정확히 입력하세요");
			ResponseEntity.ofJson(response, responseMap, 400);
			return;
		}
		if(productService.isDuplicatedProductName(reqDto.getProduct_name())) {			
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMasseage","이미 등록된 상품명입니다.");
			ResponseEntity.ofJson(response, responseMap, 400);
			return;
		}
		insertProductPespDto dto = productService.addProduct(reqDto);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data",dto);
		ResponseEntity.ofJson(response, responseMap , 201);
		return;
	}

}
