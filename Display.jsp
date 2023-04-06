<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        <style>
           body {
     font-family: sans-serif;
     background: rgb(2,0,36);
     background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 35%, rgba(0,212,255,1) 100%);
      }
.container{
  position: absolute;
  top: 40%;
  left: 50%;
  width: 800px;
  padding: 60px;
  transform: translate(-50%, -50%);
  background:grey;
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0,0,0,.6);
  border-radius: 10px;
}
h1:hover{
  background:#5B8FB9;
  color:#fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4,
              0 0 25px #03e9f4,
              0 0 50px #03e9f4,
              0 0 100px #03e9f4;

}
  table{
        width:600px;
       }

   table:hover {
             background: green;
             color: #fff;
             border-radius: 5px;
               box-shadow: 0 0 5px #03e9f4,
              0 0 25px #03e9f4,
              0 0 50px #03e9f4,
              0 0 100px #03e9f4;
           }
</style>
</style>
    
</head>
<body>

      <%
      String id = request.getParameter("userId");
      String driverName = "com.mysql.jdbc.Driver";
      String connectionUrl = "jdbc:mysql://localhost:3306/";
      String dbName = "1esa2";
      String userId = "root";
      String password = "sql@123";

      try {
    	  Class.forName(driverName);
    	  } catch (ClassNotFoundException e) {
    	  e.printStackTrace();
    	  }

          
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      %>
      <div class="container">
            <h1 align="center"><font><strong>CUSTOMER DETAILS</strong></font></h1>
      
      <table  border="1">
      <tr>

      </tr>
      <tr >
      <td><b>Name</b></td>
      <td><b>Account Number</b></td>

      </tr>
      <%
      try{ 
      connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
      statement=connection.createStatement();
      String sql ="select * from bank_info";

      resultSet = statement.executeQuery(sql);
      while(resultSet.next()){
      %>
      <tr>

      <td><%=resultSet.getString("customer_name") %></td>
      <td><%=resultSet.getString("account_no") %></td>


      </tr>

      <% 
      }

      } catch (Exception e) {
      e.printStackTrace();
      }
      %>
      </table>
      </div>
