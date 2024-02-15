package com.study.product.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class RequestUtil {
	
	public static String getJsonData(HttpServletRequest request) throws IOException {
		String requestJsonData = null;
		StringBuilder Json = new StringBuilder();
		String readData = null;
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			Json.append(readData);
		}
		requestJsonData = Json.toString();
		
		return requestJsonData;
	}
	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classOfT) throws IOException {
		String requestJsonData = null;
		StringBuilder Json = new StringBuilder();
		String readData = null;
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			Json.append(readData);
		}
		requestJsonData = Json.toString();
		Gson gson = new Gson();

		return gson.fromJson(requestJsonData, classOfT);
	}
	
}
