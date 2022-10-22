package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
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
    public Login() {
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
			
			String passdb = request.getParameter("password");
			
			PrintWriter pw  = response.getWriter();
			
//			System.out.println(uname);
//			
//			System.out.println(passdb);
		
        try {
			Class.forName(driver);
			
		   con = DriverManager.getConnection(url,dbID,dbPass);
			
			Statement st = con.createStatement();
			
			String query = "select * from loginwindow where username='"+uname+"'";
			
//			System.out.println(query);
		   
			ResultSet rs = st.executeQuery(query);
		   
		   
		   while(rs.next())
		   {
			   username  = rs.getString(1);
			   
			   password  = rs.getString(2);
			     
		   }

//		   System.out.println(username);
//		   System.out.println(password);
		   
		   
		   if(username.equals(uname) && password.equals(passdb))
			{
				response.setContentType("text/html");
//							
//				pw.println("<h2>Welcome To Servlet !!</h2>");
//				
//				pw.println("<H1> Welcome user - " + uname + "</H1>");
//				
//				pw.println("<h1> Password  - " + passdb + "</h1>");
			   
//			   pw.println("<h1 style=\"Color:red;\"> You entered wrong username and Password please try again !! </h1>");
			   RequestDispatcher rd = request.getRequestDispatcher("Submitted");
			   rd.forward(request, response);
			   
//			   rd.include(request, response);

//*********************************************************************************************
			   //We can do with this also but it not carried values and properties ......
//			   response.sendRedirect("Freelancing.html");
			   
			}
		   
		   else
			   
		   {
			   response.setContentType("text/html");
			   
//			   pw.println("<button style = \"margin-left:800px;margin-top:-100px;width:100px;height:50px;\"> Login </button>");
			   pw.println("<h1 style=\"Color:red;\"> You entered wrong username and Password please try again !! </h1>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
		   }
		   
//		   else
//		   {
//			   response.setContentType("text/html");
//			   
//			   pw.println("<h1>" + "Something went wrong please check databse connection !!" + "</h1>");
//		   }


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
