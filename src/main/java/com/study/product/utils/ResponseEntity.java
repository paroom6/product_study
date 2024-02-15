package com.study.product.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseEntity {
	public static <T> void ofJson(HttpServletResponse response, T body, int status) throws IOException {
		//<T>가 있어야 제네릭 가능 T의 매개변수나 리턴 타입에 의해 결정됨
		
		Gson gson = new Gson(); 
		response.setStatus(status);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(body));
	}
}
