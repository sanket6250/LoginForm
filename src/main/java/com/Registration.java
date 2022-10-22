package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 static Connection con;
	   String username;
	   String password;
	   String url ;
	   String driver;
	   String dbID;
	   String dbPass;
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
        driver = config.getInitParameter("driver");
		
		url = config.getInitParameter("url");
		
		dbID = config.getInitParameter("username");
		
		dbPass = config.getInitParameter("password");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("username");
		
		String email = request.getParameter("email");
		
		String mobno = request.getParameter("moNo");
		
		String passdb = request.getParameter("Cpassword");
				
		PrintWriter pw  = response.getWriter();
		
		boolean flag;
		
		try {
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, dbID, dbPass); 
					
			Statement st = con.createStatement();
			
			String query = "insert into loginwindow values ('"+uname+"','"+passdb+"','"+email+"','"+mobno+"')";
			
			int i = st.executeUpdate(query);
			
			flag = true;
			
			if(flag==true)
			{
				response.setContentType("text/html");
//				pw.println("<form action = \"submit.html\" method=\"post\" >");
////				pw.println("<h1>Submitted Successfully.....</h1>");
//				pw.println("</from>");
			    	
				RequestDispatcher rd = request.getRequestDispatcher("submit.html");
				rd.forward(request, response);
				pw.println("<a href=\"Login.html\"> <button style = \"width:100px;height:50px;\"> Login </button></a>");
			}
			
			else
			{
				response.setContentType("text/html");
				pw.println("<h1 style=\"Color:red;\"> Something went wrong please try again !! </h1>");
				RequestDispatcher rd = request.getRequestDispatcher("Registration Form.html");
				rd.include(request, response);
			}
			
			
//			response.sendRedirect("submit.html");
		    
//			System.out.println(i + "  number of record added");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
