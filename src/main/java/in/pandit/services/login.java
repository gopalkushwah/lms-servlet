package in.pandit.services;

import java.io.IOException;

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


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetching data from login from
			
				String email = request.getParameter("email").trim();
				String password = request.getParameter("pass").trim();
				// Declared variable for storing data, fetched from database
				try {
					User user = UserDao.loginUser(email, password);
					if (user.getName() != null) {
						if (user.getIsAdmin().equals("false") && user.getIsSuperAdmin().equals("false")) {
							HttpSession session = request.getSession();
							session.setAttribute("email", email);
							user.setPassword("");
							CookiesHelper.setCookies(user, response, "user");
							response.sendRedirect("dashboard.jsp");
						}else if(user.getIsAdmin().equals("a") && user.getIsSuperAdmin().equals("false")) {
							HttpSession session = request.getSession();
							session.setAttribute("email", email);
							user.setPassword("");
							CookiesHelper.setCookies(user, response, "user");
							response.sendRedirect("admin.jsp");
						}else if(user.getIsAdmin().equals("false") && user.getIsSuperAdmin().equals("s")) {
							HttpSession session = request.getSession();
							session.setAttribute("email", email);
							user.setPassword("");
							CookiesHelper.setCookies(user, response, "user");
							response.sendRedirect("superadmin.jsp");
						}else {
							request.setAttribute("error", "Invalid Email or Password !");
							RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
							rd.include(request, response);
						}
					}else {
						request.setAttribute("error", "Invalid Email or Password !");
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.include(request, response);
					}
				}catch (Exception e) {
					response.sendRedirect("index.jsp");
					System.out.println(e);
				}
	}

}
