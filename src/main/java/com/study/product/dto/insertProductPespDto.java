package com.study.product.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class insertProductPespDto {
	private int succesCount;
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;
	
	
}
