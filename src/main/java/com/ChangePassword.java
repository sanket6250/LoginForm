package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 static Connection con;
	   String url ;
	   String driver;
	   String dbID;
	   String dbPass;
	   String dbEmail;
	   
    public ChangePassword() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("oldpass");
		
		String confPass = request.getParameter("cpass");
		
				
		PrintWriter pw  = response.getWriter();
		
		try {
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, dbID, dbPass); 
					
			Statement st = con.createStatement();
			
			String query = "update loginwindow set pass='"+confPass+"' where emailid='"+email+"'";
			
            String query2 = "select * from loginwindow where emailid='"+email+"'";
			
//			System.out.println(query);
		   
			ResultSet rs = st.executeQuery(query2);
		   
		   
		   while(rs.next())
		   {
			   
			     dbEmail = rs.getString(3);
		   }
		   
			if(email.equals(dbEmail))
			{
				int i = st.executeUpdate(query);
				response.setContentType("text/html");
				pw.println("<form action = \"submit.html\" method=\"post\" >");
//				pw.println("<h1>Submitted Successfully.....</h1>");
				pw.println("</from>");
				
				System.out.println(i + "  number of record added");
			}
		    
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
