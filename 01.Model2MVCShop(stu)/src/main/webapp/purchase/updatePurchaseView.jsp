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

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%=pvo.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%=cvo.getBuyer() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
			���ݱ���
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>010-1234-5678</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>����� ���ʱ�</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>������ ���ּ���</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>2022-04-30</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>