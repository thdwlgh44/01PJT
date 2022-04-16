<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>

<%
	ProductVO pvo = (ProductVO)request.getAttribute("pvo");
	PurchaseVO cvo = (PurchaseVO)request.getAttribute("cvo");
%>
<!DOCTYPE html>
<html>
<head>
<title>AddPurchaseAction</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=cvo.getTranNo() %>" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=pvo.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=cvo.getBuyer() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			현금구매
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%= %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>010-1234-5678</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>서울시 서초구</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>안전히 와주세요</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>2022-04-30</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>