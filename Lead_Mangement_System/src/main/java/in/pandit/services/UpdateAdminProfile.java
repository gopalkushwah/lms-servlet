package in.pandit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.UserDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.User;


@WebServlet("/UpdateAdminProfile")
public class UpdateAdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String userId = request.getParameter("user-id");
		User user = UserDao.getUserByUserId(userId);
		user.setName(name);
		user.setPassword(password);
		user.setMobile(mobile);
		try {
			boolean check = UserDao.updateAdminInformation(user);
			if(check) {
				CookiesHelper.setCookies(user, response, "user");
				HttpSession session = request.getSession();
				session.setAttribute("msg", "Data Updated Successfully");
				response.sendRedirect("adminProfile.jsp");	
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("msg", "Data Not Updated Successfully");
				response.sendRedirect("adminProfile.jsp");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
