<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@page import="java.util.List"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
   Map<String, Object> map =(Map<String,Object>)request.getAttribute("map");
   SearchVO searchVO = (SearchVO)request.getAttribute("searchVO");
   UserVO userVO = (UserVO)session.getAttribute("user");
   
   int total = 0;
   List<PurchaseVO> list = new ArrayList<PurchaseVO>();
   if(map != null){
      total = ((Integer)map.get("count")).intValue();
      list = (List<PurchaseVO>)map.get("list");
   }   
   
   int currentPage = searchVO.getPage();
   
   int totalPage=0;
   if(total>0){
      totalPage=total/searchVO.getPageUnit();
      if(total%searchVO.getPageUnit()>0){
         totalPage +=1;
      }
   }
%>
<!DOCTYPE html>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
   function fncGetUserList() {
      document.detailForm.submit();
   }
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/getPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"   cellspacing="0">
   <tr>
      <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
      <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="93%" class="ct_ttl01">구매 목록조회</td>
            </tr>
         </table>
      </td>
      <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"   width="12" height="37"></td>
   </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"   style="margin-top: 10px;">
   <tr>
      <td colspan="11">전체 <%=total %>건수,<%=currentPage %>페이지</td>
   </tr>
   <tr class="ct_list_pop">
      <td class="ct_list_b" width="100">No</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">회원ID</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">회원명</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">전화번호</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">배송현황</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">정보수정</td>
   </tr>
   <tr>
      <td colspan="11" bgcolor="808285" height="1"></td>
   </tr>
   <%
      int no =list.size();
      for(int i=0; i<list.size(); i++){
         PurchaseVO vo =(PurchaseVO)list.get(i);
         System.out.println(vo);
   %>
   <tr>
      <td align="center"><%=no--%></td>
      <td></td>
      <td class="ct_list_b" width="150"><a href="/getPurchase.do?tranNo=<%=vo.getTranNo() %>"><%= vo.getBuyer().getUserId() %></a></td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150"><%= userVO.getUserName() %></td>
      <td class="ct_line02"></td>
      <td class="ct_list_b"><%=  vo.getReceiverPhone() %></td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">
         <%if(vo.getTranCode().equals("1  ")) {%>
            현재 구매완료 상태 입니다.
         <%} else if(vo.getTranCode().equals("2  ")) {%>
            현재 배송중 상태 입니다.
         <%} else {%>
            현재 배송완료 상태 입니다.
         <%} %>
      </td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">
         <% if(vo.getTranCode().equals("2  ")) {%>
            <a href="/updateTranCode.do?prodNo=<%=vo.getPurchaseProd().getProdNo()%>&tranCode=3&con=user">물건도착</a>
         <%} else {%>
         <%} %>
      </td>
   </tr>
   <tr>
      <td colspan="11" bgcolor="808285" height="1"></td>
   </tr>
   <% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
   <tr>
      <td align="center">
      <% for(int i=1; i<=totalPage; i++) {%>
         <a href="/listPurchase.do?page=<%=i%>"><%=i %></a>
      <%} %>
      </td>
   </tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>
</body>
</html>
<script>
   function fncGetProductList(){
      document.detailForm.submit();
   }
</script>
