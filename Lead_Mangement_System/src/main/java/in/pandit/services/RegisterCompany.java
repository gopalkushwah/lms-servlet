package in.pandit.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.RegisterDao;
import in.pandit.model.User;
import in.pandit.persistance.DatabaseConnection;


@WebServlet("/RegisterCompany")
public class RegisterCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		/* *******************************************
		 * ****** GETTING INFOR FROM REQUEST *********
		 * ******************************************* 
		 */
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		try {
			/* *******************************************
			 * ****** BINDING INFO IN USER OBJECT ********
			 * ******************************************* 
			 */
			User user = new User();
			user.setEmail(email);
			user.setMobile(mobile);
			user.setName(username);
			user.setPassword(password);
			
			/* **************************************************
			 * ****** CALL INSERT METHOD OF REGISTER DAO ********
			 * **************************************************
			 */
			int status = RegisterDao.insertCompany(user,request);
			
			if(status > 0) {
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('You have registered successfully');");
				pw.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
			else {
				pw.print("Something wrong with database");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}

}
