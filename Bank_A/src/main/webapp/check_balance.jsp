<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>System 2 Bank</title>
</head>
<h1>Online Banking System</h1>
<h2>Check Balance</h2>
<body>
<form method="POST">
<table>
<tr>
    <td>Account</td>
    <td>:</td>
    <td><input type="text" id="account" name="account" /></td>
  </tr>
  
  <tr>
    <td>PIN</td>
    <td>:</td>
    <td><input type="text" id="pin" name="pin" /></td>
  </tr>
  
</table>
<br>
<input type="submit" value="Check Balance" />  
</form>

<% 
try{
String acc = request.getParameter("account");
String pin = request.getParameter("pin");
//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
Class.forName("oracle.jdbc.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:fsktm", "nky", "nky");
PreparedStatement stmt = conn.prepareStatement("SELECT id, name, balance FROM accounts where id=? and online_pin=?");
stmt.setString(1, acc);
stmt.setString(2, pin);
ResultSet rs = stmt.executeQuery();

	%>
	<br>
	<table border=1>
    <tr>
    	<th>Account</th>
        <th>Name</th>
        <th>Balance</th>
        
    </tr>
    <%
    while (rs.next()) {
    %>
    <tr>
    	<td><%= rs.getString("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("balance") %></td>
        
    </tr>
    <%
    }
    
}catch(Exception e){
	e.printStackTrace();
	out.println("Couldnt connect to database");
}
    %>
</table>
</body>
</html>