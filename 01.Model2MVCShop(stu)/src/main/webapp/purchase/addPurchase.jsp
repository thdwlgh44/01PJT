<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>

<%
	UserVO userVO = (UserVO)session.getAttribute("user");
	System.out.println(userVO);
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
	System.out.println(productVO);
	PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
	System.out.println(purchaseVO);
	String paymentOption = (String)request.getAttribute("paymentOption");
%>

<html>
<head>
<title>구매 버튼 클릭시 insert</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=purchaseVO.getPurchaseProd().getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=purchaseVO.getBuyer().getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
			<%=purchaseVO.getPaymentOption() %>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>