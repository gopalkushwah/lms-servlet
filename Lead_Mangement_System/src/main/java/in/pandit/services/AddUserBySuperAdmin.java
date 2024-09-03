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
import in.pandit.model.User;

/**
 * Servlet implementation class UpdateAdminBySuperAdmin
 */
@WebServlet("/AddUserBySuperAdmin")
public class AddUserBySuperAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String companyId = request.getParameter("company-id");
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setIsAdmin("false");
		user.setIsSuperAdmin("false");
		user.setCompanyId(companyId);
		user.setGender(gender);
		user.setEmail(email);
		user.setPassword(password);
		boolean b = UserDao.addUser(user);
		if(b) {
			response.sendRedirect("allUsersSuperAdmin.jsp");
		}else {
			response.sendRedirect("allUsersSuperAdmin.jsp");
		}
		
	}

}
