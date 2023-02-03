<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>System 3 Merchant</title>
</head>

<h1>Mobile Top Up</h1>
<body>
<form action ="PaymentServlet" method = "POST" >
<table>
	
  <tr>
    <td>Account</td>
    <td>:</td>
    <td><input type="text" id="account" name="id" /></td>
  </tr>
  
  <tr>
    <td>PIN</td>
    <td>:</td>
    <td><input type="text" id="pin" name="pin" /></td>
  </tr>
  
  <tr>
    <td>Top up(RM)</td>
    <td>:</td>
    <td><input type="text" name="topup" id="topup"></td>
  </tr>
  
  
</table>
<br>
<input type="submit" name = "pay" value="PAY" >
</form>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null){
        out.println("<p style='color:red'>" + errorMessage + "</p>");
    }
%>
</body>
</html>