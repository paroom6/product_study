package com.study.product.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.product.dto.UserDto;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/auth/signIn")
public class signInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public signInServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto dbUser = (UserDto) request.getServletContext().getAttribute("dbUser");
		
		UserDto signinUserDto = RequestUtil.convertJsonData(request, UserDto.class);
		
		boolean isMatchObjectsUsername = Objects.equals(dbUser.getUsername(), signinUserDto.getUsername());
		boolean isMatchObjectsPassword = Objects.equals(dbUser.getPassword(), signinUserDto.getPassword());
		
		if(!(isMatchObjectsUsername && isMatchObjectsPassword)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("errorMassage", "사용자 정보를 다시 확인하세요");
			ResponseEntity.ofJson(response, errorMap, 403);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("principalUser", dbUser);
		
		ResponseEntity.ofJson(response, dbUser, 200);
	
	}

}
