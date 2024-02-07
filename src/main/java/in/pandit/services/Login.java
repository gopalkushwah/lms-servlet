package in.pandit.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.UserDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.User;
import in.pandit.persistance.DatabaseConnection;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetching data from Login from
			
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		
		// Declared variable for storing data, fetched from database
		try {
			User user = UserDao.loginUser(email, password);
			System.out.println(Objects.nonNull(user));
			if (Objects.nonNull(user)) {
				if (user.getIsAdmin().equals("false")) {
					System.out.println("Employee");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					user.setPassword("");
					CookiesHelper.setCookies(user, response, "user");
					response.sendRedirect("dashboard.jsp");
				}
				else if(user.getIsAdmin().equals("true")) {
					System.out.println("Company");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					user.setPassword("");
					CookiesHelper.setCookies(user, response, "user");
					response.sendRedirect("admin.jsp");
				}
				else if(user.getIsSuperAdmin().equals("s")) {
					System.out.println("Super admin");
					
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					user.setPassword("");
					CookiesHelper.setCookies(user, response, "user");
					response.sendRedirect("superadmin.jsp");
				}
				else {
					System.out.println("inside the inner else but not matched");
					
					request.setAttribute("error", "Invalid Email or Password !");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
				}
			}else {
				System.out.println("inside the outer else but not matched");
				request.setAttribute("error", "Invalid Email or Password !");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
