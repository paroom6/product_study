package com.study.product.service;

import java.util.ArrayList;
import java.util.List;

import com.study.product.dao.ProductDao;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.dto.insertProductPespDto;
import com.study.product.dto.insertProductReqDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDao productDao;
	
	
	private ProductService() {
		productDao = ProductDao.getInstance();		
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean isDuplicatedProductName(String productName) {
		return productDao.findProductByProductName(productName) != null;
	}
	
	public List<SearchProductRespDto> searchProduct() {
		return SearchProductRespDto.builder().build().toDtoList() ;
	}
	
	public insertProductPespDto addProduct(insertProductReqDto reqDto) {
		ProductVo productVo = reqDto.toVo();
		int successCount = productDao.saveProduct(productVo);
		return productVo.toInsertDto(successCount);
	}
}
