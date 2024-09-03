package in.pandit.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.UserDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.User;

/**
 * Servlet implementation class UpdateAdminBySuperAdmin
 */
@WebServlet("/AddUserByAdmin")
public class AddUserByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		User userByCookie = CookiesHelper.getUserCookies(request, "user");
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setIsAdmin("false");;
		user.setIsSuperAdmin("false");;
		user.setEmail(email);
		user.setPassword(password);
		user.setGender(gender);
		user.setCompanyId(userByCookie.getUserId());
		boolean b = UserDao.addUser(user);
		if(b) {
			response.sendRedirect("allUsers.jsp");
		}else {
			response.sendRedirect("allUsers.jsp");
		}
		
	}

}
