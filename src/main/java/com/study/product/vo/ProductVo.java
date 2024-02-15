package com.study.product.vo;

import com.study.product.dto.SearchProductRespDto;
import com.study.product.dto.insertProductPespDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class ProductVo {//entity
	private int product_id;
	private String product_name;
	private int product_price;
	private String product_size;

public insertProductPespDto toInsertDto(int successCount) {
	return insertProductPespDto.builder()
						.succesCount(successCount)
						.productId(product_id)
						.productName(product_name)
						.productPrice(product_price)
						.productSize(getProduct_size())
						.build();
						
}

public SearchProductRespDto toSearchDto() {
	return SearchProductRespDto.builder()
							.product_id(product_id)
							.product_name(product_name)
							.product_price(product_price)
							.product_size(product_size)
							.build();
}


}

