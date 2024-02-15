package com.study.product.dto;

import com.study.product.vo.ProductVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class insertProductReqDto {//data transform object 요청과 응답에 쓰이는 메서드
	private String product_name;
	private int product_price;
	private String product_size;
	public ProductVo toVo() {
		return ProductVo.builder()
				.product_name(product_name)
				.product_price(product_price)
				.product_size(product_size)
				.build();
	}
}
