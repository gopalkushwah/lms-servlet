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

@WebServlet("/updateUserProfile")
public class updateUserProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		User user = CookiesHelper.getUserCookies(request, "user");
		if (!mobile.equals(user.getMobile())) {
			User userByMobile = UserDao.getUserByMobile(mobile);
			if (userByMobile.getName() == null) {
				user.setMobile(mobile);
			} else {
				session.setAttribute("update", "Mobile number already available");
				response.sendRedirect("updateProfileForm.jsp");
				return;
			}
		}
		user.setEmail(email);
		user.setName(name);
		user.setGender(gender);
		boolean check = UserDao.updateUserInformation(user);
		if (check) {
			session.setAttribute("update", "Data Updated Successfully");
			response.sendRedirect("profile.jsp");
		} else {
			session.setAttribute("update", "Data Not Updated Successfully");
			response.sendRedirect("profile.jsp");
		}

	}

}
