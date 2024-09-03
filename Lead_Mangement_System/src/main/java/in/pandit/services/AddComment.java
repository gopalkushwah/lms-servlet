package in.pandit.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.CommentDao;
import in.pandit.dao.UserDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.Comment;
import in.pandit.model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leadid = request.getParameter("leadid");
		String commentText = request.getParameter("comment");
		HttpSession session = request.getSession();
		User user = CookiesHelper.getUserCookies(request, "user");
		try {
			Comment comment = new Comment();
			comment.setComment(commentText);
			comment.setUserId(user.getUserId());
			comment.setLeadId(leadid);
			int done = CommentDao.insertComment(comment);
			if(done>0) {
				session.setAttribute("comment", "Comment Inserted successful");
				response.sendRedirect("user-all-leads.jsp");
			}else {
				response.sendRedirect("dashboard.jsp");
				session.setAttribute("comment", "Something went wrong! please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
