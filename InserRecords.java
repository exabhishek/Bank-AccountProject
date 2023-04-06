package http;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createlink")
public class InserRecords extends HttpServlet
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("fullname");
		String accountno=req.getParameter("acno");
		String ammount=req.getParameter("bal");
		
		//Parsing   
		double amnt=Double.parseDouble(ammount); 
		
		PreparedStatement pstmt=null;
		
		 String query="insert into bank_info values(0,?,?,?)";
		  
		 try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, accountno);
			pstmt.setDouble(3, amnt);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			RequestDispatcher rd=req.getRequestDispatcher("/Index.html");
			resp.getWriter().print("<h1 style='color:green'>"+count+" Records Inserted Successfully...");
			rd.include(req, resp);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	

}
