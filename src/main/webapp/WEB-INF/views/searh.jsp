<%@page import="com.study.product.dto.SearchProductRespDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%
  //jsp의 jstl이 붙은  core를 가져와야 한다.
  /// <c:forEach var="dto" items="${productList}"> Attribute의 key 이다.
  // getter 가 존재해야 출력가능하다.
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>번호</th>	
			<th>상품명</th>	
			<th>가격</th>	
			<th>사이즈</th>	
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${productList}" >
		<c:if test="${dto.product_id % 2 == 0 }">
			<tr>
				<td>
					${dto.product_id}
				</td>
				<td>
					${dto.product_name}
				</td>
				<td>
					${dto.product_price}
				</td>
				<td> 
					${dto.product_size}
				</td>
			</tr>
		</c:if >
		<c:choose>
		
		<%//choose when으로 if, else if 를 하고 otherwise로 else를 지정한다. %>
			<c:when test="">
			</c:when>
			<c:when test="">
			</c:when>
			<c:when test="">
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</tbody>
</table>
</body>
</html>