<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.List"  %>

<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page import="com.model2.mvc.common.Search" %>

<%
	List<Purchase> list= (List<Purchase>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>



<html>
<head>
<title>상품 관리</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetSaleList(){
   document.detailForm.submit();
}
<!--
function fncGetProductList(){
   document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=search" method="post">

<table width="100%" height="37" border="0" cellpadding="0"   cellspacing="0">
   <tr>
      <td width="15" height="37">
         <img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
      </td>
      <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="93%" class="ct_ttl01">
               
                     판매 목록조회
               
               </td>
            </tr>
         </table>
      </td>
      <td width="12" height="37">
         <img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
      </td>
   </tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
   <tr>
 <%
      if(search.getSearchCondition() != null) {
   %>
      <td align="right">
         <select name="searchCondition" class="ct_input_g" style="width:80px">
      <%
            if(search.getSearchCondition().equals("0")){
      %>
            <option value="0" selected>주문번호</option>
            <option value="1">상품번호</option>
            <option value="2">배송상태코드</option>
      <%
            }else if(search.getSearchCondition().equals("1")){
      %>
            <option value="0">주문번호</option>
            <option value="1" selected>상품번호</option>
            <option value="2">배송상태코드</option>
      <%
            }else{
       %>
            
            <option value="0" >주문번호</option>
            <option value="1">상품번호</option>
            <option value="2" selected>배송상태코드</option>
      <%   
            }
      %>
         </select>
         <input    type="text" name="searchKeyword"  value="<%=search.getSearchKeyword() %>" 
                     class="ct_input_g" style="width:200px; height:19px" >
      </td>
   <%
      }else{
   %>
      <td align="right">
         <select name="searchCondition" class="ct_input_g" style="width:80px">
            <option value="0">주문번호</option>
            <option value="1">상품번호</option>
            <option value="1">배송상태코드</option>
         </select>
         <input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
      </td>
   <%
      }
   %>
      
      <td align="right" width="70">
         <table border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="17" height="23">
                  <img src="/images/ct_btnbg01.gif" width="17" height="23">
               </td>
               <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
                  <a href="javascript:fncGetProductList();">검색</a>
               </td>
               <td width="14" height="23">
                  <img src="/images/ct_btnbg03.gif" width="14" height="23">
               </td>
            </tr>
         </table>
      </td>
   </tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
   <tr>
      <td colspan="11" >전체 <%= total%> 건수, 현재 <%=currentPage %> 페이지</td>
   </tr>
   <tr>
      <td class="ct_list_b" width="100">No</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">상품명</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">가격</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">등록일</td>   
      <td class="ct_line02"></td>
      <td class="ct_list_b">현재상태</td>   
   </tr>
   <tr>
      <td colspan="11" bgcolor="808285" height="1"></td>
   </tr>
    <%    
      int no=list.size();
      for(int i=0; i<list.size(); i++) {
         Product vo = (Product)list.get(i);
   %>
   <tr class="ct_list_pop">
      <td align="center"><%=no--%></td>
      <td></td>  
           <td align="left"><a href="/updateProductView.do?prodNo=<%=vo.getProdNo() %>"><%= vo.getProdName() %></a>
      <td></td>
      <td align="left"><%= vo.getPrice() %></td>
      <td></td>
      <td align="left"><%= vo.getRegDate() %></td>
      <td></td>
      <td align="left"><%= vo.getProTranCode() %></td> 
        
   </tr>
   <tr>
      <td colspan="11" bgcolor="D6D7D6" height="1"></td>
   </tr> 
   <% } %>
 </table> 
 

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
   <tr>
      <td align="center">
        <%
         for(int i=1;i<=totalPage;i++){
      %>
         
         <a href="/listSale.do?page=<%=i%>"><%=i %></a>
      
       <%
         }
      %>
       </td>
   </tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>