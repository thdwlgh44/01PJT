<%@ page language="java" contentType="text/html; charset=UTF-8" %>


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
		<td>10000</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>admin</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			신용구매
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>admin</td>
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
		<td>2022-04-17</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>