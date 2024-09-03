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

@WebServlet("/UpdateUserByAdmin")
public class UpdateUserByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");
		user.setName(name);
		user.setMobile(mobile);
		user.setGender(gender);
		boolean b = UserDao.updateUserInformation(user);
		if(b) {
			response.sendRedirect("allUsers.jsp");
		}else {
			response.sendRedirect("allUsers.jsp");
		}
		
	}

}
