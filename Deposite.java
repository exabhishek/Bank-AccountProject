package http;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/indexform")
public class Deposite extends HttpServlet
{
	
	Connection con;
	
	public void init() throws ServletException
	   {
		   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1esa2","root","sql@123");
			
		}
		   catch (ClassNotFoundException e)
		   {
		
			e.printStackTrace();
		   } 
		   catch (SQLException e) 
		   {
			
			e.printStackTrace();
		}
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountno=req.getParameter("acno");
		String ammount1=req.getParameter("ammount");
		String btn=req.getParameter("text");
		//Parsing
		Double ammount2=Double.parseDouble(ammount1);
		
		   
		 String query="select * from bank_info where account_no='"+accountno+"'";
		
		 
		 try {
			 ResultSet rs=null; 
			  Statement stmt;
			
			 stmt=con.createStatement();
			 rs=stmt.executeQuery(query);
		    
			 while(rs.next())
			 {
				
				 double bal=rs.getDouble(4);
				 double Nbal=bal+ammount2;
				 if(btn.equals("deposite"))
				 {
				 String query1="update bank_info set ammount='"+Nbal+"' where account_no='"+accountno+"'";
				 stmt=con.createStatement();
				 stmt.executeUpdate(query1);
				 PrintWriter pw=resp.getWriter();
			     RequestDispatcher rd=req.getRequestDispatcher("/Index.html");
				 pw.print("<h1 style='color:green'> balance deposite  successfully...</h1>");
				 rd.include(req, resp);
				 break;
				 }
				 else 
				 {
					if(ammount2<bal)
					{
					
					 double Nbal2=bal-ammount2; 
					 String query1="update bank_info set ammount='"+Nbal2+"' where account_no='"+accountno+"'";
				     stmt=con.createStatement();
					 stmt.executeUpdate(query1);
					 PrintWriter pw=resp.getWriter();
				     RequestDispatcher rd=req.getRequestDispatcher("/Index.html");
					 pw.print("<h1 style='color:green'> balance withdraw  successfully...</h1>");
					 rd.include(req, resp);
					 break;
					}
					else
					{
						 PrintWriter pw=resp.getWriter();
					     RequestDispatcher rd=req.getRequestDispatcher("/Index.html");
						 pw.print("<h1 style='color:red'> Insufficient Balance Please Enter the Valid Ammount ...</h1>");
						 rd.include(req, resp);
					}
				 }
			 }
				
				
				 
			 }
			
			 
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
			  
	}
}
