package com.study.product.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.study.product.dao.ProductDao;
import com.study.product.vo.ProductVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchProductRespDto { //클라이언트 Json-Dto 서블릿 Dto-Dto 서비스 Dto-Vo Dao 항상 형식을 맞추어야한다.
	private int product_id;
	private String product_name;
	private int product_price;
	private String product_size;
	private ProductDao productDao;
	
	
	public List<SearchProductRespDto> toDtoList() {
//		List<SearchProductRespDto> products = new ArrayList<>();
//		for(ProductVo product : productlist) {
//			SearchProductRespDto dto = product.toSearchDto(product);
//			products.add(dto);
//		}
		productDao = ProductDao.getInstance();
		//js의 경우
		//[1,2,3,4,5] -> [2,4,6,8,10]
		//let nums= [1,2,3,4,5];
		//nums.map(num=>num*2);[2,4,6,8,10]
		//productDao.searchProduct().stream() map메서드는 stream을 사용해야한다.
//			.map(vo -> vo.toSearchDto())//가공
//			.collect(Collectors.toList());//변환
//		Stream<String> strStream = Stream.of("a", "b", "c", "d");
//		strStream.peek(str -> System.out.println(str)).collect(Collectors.toList());//출력 list로 변환
		//System.out::println(str) System.out의 println를 사용해라
//		List<String> strList =  strStream.collect(Collectors.toList());
		return productDao.searchProduct().stream()
				.map(vo -> vo.toSearchDto())
			  //.map(ProductVo::toSearchDto)
				.collect(Collectors.toList());
	}
	
}
