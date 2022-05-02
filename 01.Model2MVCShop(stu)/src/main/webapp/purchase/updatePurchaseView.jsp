<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% PurchaseVO vo = (PurchaseVO)request.getAttribute("purchaseVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript" src="../javascript/calendar.js"></script>
</head>
<body bgcolor="#ffffff" text="#000000">
<form name="detailForm" method="post">
<table width="100%" height="37" border="0" cellpadding="0"   cellspacing="0">
   <tr>
      <td width="15" height="37">
         <img src="/images/ct_ttl_img01.gif" width="15" height="37">
      </td>
      <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
            <td width="93%" class="ct_ttl01">구매하기</td>
            <td width="20%" align="right">&nbsp;</td>
         </tr>
      </table>
      </td>
      <td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
   </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
   <tr>
      <td height="1" colspan="3" bgcolor="D6D6D6"></td>
   </tr>
   <tr>
      <td width="104" class="ct_write">
         구매자아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
      </td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="105"><input name="prodNo" value="<%=vo.getReceiverName() %>" readonly/></td>
               <td>   </td>
            </tr>
         </table>
      </td>
   </tr>
   <tr>
      <td height="1" colspan="3" bgcolor="D6D6D6"></td>
   </tr>
   
   <tr>
      <td width="104" class="ct_write">구매방법</td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01">
         <select name ="paymentOption" id="paymentOption">
            <option value="a">현금구매</option>
            <option value="b">카드구매</option>
         </select>
      </td>
   </tr>
   
   <tr>
      <td width="104" class="ct_write">구매자이름</td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01"><input name="receiverName" value="<%= vo.getReceiverName() %>" /></td>
   </tr>

   <tr>
      <td height="1" colspan="3" bgcolor="D6D6D6"></td>
   </tr>   
   
   <tr>
      <td width="104" class="ct_write">구매자연락처</td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01"><input id="receiverPhone" name="receiverPhone" value="<%=vo.getReceiverPhone() %>" /></td>
   </tr>
   
   <tr>
      <td width="104" class="ct_write">구매자주소</td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01"><input id="divyAddr" name="divyAddr" value="<%=vo.getDivyAddr() %>" /></td>
   </tr>
   
   <tr>
      <td width="104" class="ct_write">구매요청사항</td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01"><input id="divyRequest" name="divyRequest" value="<%=vo.getDivyRequest() %>" /></td>
   </tr>
   
   <tr>
      <td width="104" class="ct_write">
         배송희망일자 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
      </td>
      <td bgcolor="D6D6D6" width="1"></td>
      <td class="ct_write01">
         <input type="text" name="divyDate" readonly="readonly" class="ct_input_g"  value="<%=vo.getDivyDate().split(" ")[0].replace("-", "") %>"
                  style="width: 100px; height: 19px"   maxLength="10" minLength="6"/>
            &nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
                              onclick="show_calendar('document.detailForm.divyDate', document.detailForm.divyDate.value)"/>
      </td>
   </tr>

   <tr>
      <td height="1" colspan="3" bgcolor="D6D6D6"></td>
   </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"   style="margin-top:10px;">
   <tr>
      <td width="53%"></td>
      <td align="right">
         <table border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="17" height="23">
                  <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
               </td>
               <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                  <a href="javascript:fncUpdateProdurchase();">수정</a>
               </td>
               <td width="14" height="23">
                  <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
               </td>
               <td width="30"></td>               
               <td width="17" height="23">
                  <img src="/images/ct_btnbg01.gif" width="17" height="23"/>
               </td>
               <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                  <a href="javascript:history.go(-1);">취소</a>
               </td>
               <td width="14" height="23">
                  <img src="/images/ct_btnbg03.gif" width="14" height="23"/>
               </td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</form>
</body>
</html>

<script type="text/javascript">
function fncUpdateProdurchase(){
   document.detailForm.action='/updatePurchase.do?tranNo=<%=vo.getTranNo()%>';
   document.detailForm.submit();
}

function resetData(){
   document.detailForm.reset();
}
</script>