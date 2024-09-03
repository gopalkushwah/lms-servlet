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
import in.pandit.model.Comment;
import in.pandit.model.User;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddSuperAdminComment")
public class AddSuperAdminComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSuperAdminComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int leadid = Integer.parseInt(request.getParameter("leadid"));
		String useremail = request.getParameter("useremail");
		String commentText = request.getParameter("comment");
		HttpSession session = request.getSession();
		User user = UserDao.getUserByEmail(useremail);
		try {
			Comment comment = new Comment();
			comment.setComment(commentText);
			comment.setUserid(user.getId());
			comment.setUseremail(useremail);
			comment.setLeadid(leadid);
			int done = CommentDao.insertComment(comment);
			System.out.println(done);
			if(done>0) {
				session.setAttribute("comment", "Comment Inserted successful");
				response.sendRedirect("allLeadsSuperAdmin.jsp");
			}else {
				session.setAttribute("comment", "Something went wrong! please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
