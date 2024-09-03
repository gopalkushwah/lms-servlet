package in.pandit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.UserDao;
import in.pandit.model.User;

@WebServlet("/AddUserByAdmin")
public class AddUserByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		User userByMobile = UserDao.getUserByMobile(mobile);
		if(userByMobile.getName()==null){
			User userByEmail = UserDao.getUserByEmail(email);
			System.out.println(userByEmail.getName());
			if(userByEmail.getName()==null){
				User user = new User();
				user.setName(name);
				user.setMobile(mobile);
				user.setIsAdmin("false");
				user.setIsSuperAdmin("false");
				user.setGender(gender);
				user.setEmail(email);
				user.setPassword(password);
				boolean b = UserDao.addUser(user);
				if(b) {
					response.sendRedirect("allUsers.jsp");
				}else {
					response.sendRedirect("allUsers.jsp");
				}
			}else {
				session.setAttribute("already", "User already registered with same email!");
				response.sendRedirect("admin.jsp");
			}
		}else {
			session.setAttribute("already", "User already registered with same mobile!");
			response.sendRedirect("admin.jsp");
		}
		
	}

}
