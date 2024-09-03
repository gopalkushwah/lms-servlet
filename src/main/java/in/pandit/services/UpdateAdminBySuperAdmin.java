package in.pandit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.CommentDao;
import in.pandit.dao.LeadDao;
import in.pandit.dao.UserDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.User;

@WebServlet("/UpdateAdminBySuperAdmin")
public class UpdateAdminBySuperAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		HttpSession session = request.getSession();
		User user = CookiesHelper.getUserCookies(request, "userInfo");
		String userEmail = user.getEmail();
		
		if(!mobile.equals(user.getMobile())) {
			User userByMobile = UserDao.getUserByMobile(mobile);
			if(userByMobile.getName()==null) {
				user.setMobile(mobile);
			}else {
				session.setAttribute("update", "Mobile number already available");
				response.sendRedirect("update-admin-by-super-admin.jsp?update="+user.getEmail());
				return;
			}
		}
		if(!email.equals(user.getEmail())) {
			User getUserByEmail = UserDao.getUserByEmail(email);
			if(getUserByEmail.getName()==null) {
				LeadDao.updateCurrentOwnerEmail(email, userEmail);
				LeadDao.updateOwnerEmail(email, userEmail);
				CommentDao.updateCommentUserEmail(email, userEmail);
				user.setEmail(email);
			}else {
				session.setAttribute("update", "Email number already available");
				response.sendRedirect("update-admin-by-super-admin.jsp?update="+user.getEmail());
				return;
			}
		}
		
		user.setName(name);
		user.setGender(gender);
		boolean b = UserDao.updateUserInformation(user);
		if(b) {
			
			response.sendRedirect("allAdmin.jsp");
		}else {
			response.sendRedirect("allAdmin.jsp");
		}
		
	}

}
