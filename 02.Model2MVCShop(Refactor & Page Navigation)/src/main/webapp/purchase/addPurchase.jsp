<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.service.domain.User" %>


<%
	User uvo = (User)session.getAttribute("user");

%>



<%
	Purchase pcvo=(Purchase)request.getAttribute("purchase");
	System.out.println(pcvo);

%>





<html>
<head>
<title>���� �Ϸ� ������</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
      <td><%=pcvo.getPurchaseProd().getProdNo() %></td>
      	<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%=uvo.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
	  <%
            if(pcvo.getPaymentOption().trim().equals("1")){
      %>
			���ݱ���
      <%
            }else {
      %>
            �ſ뱸��
      <%
            }
	  %>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%=pcvo.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%=pcvo.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%=pcvo.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%=pcvo.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%=pcvo.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>