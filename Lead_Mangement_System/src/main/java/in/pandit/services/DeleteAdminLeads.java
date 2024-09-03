package in.pandit.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.CommentDao;
import in.pandit.dao.LeadDao;
import in.pandit.persistance.DatabaseConnection;


@WebServlet("/DeleteAdminLeads")
public class DeleteAdminLeads extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leadId = request.getParameter("delete");
		try {
			int isDeleted = LeadDao.deleteLeadLeadsId(leadId);
			if(isDeleted > 0) {
				CommentDao.deleteCommentByLeadId(leadId);
				HttpSession session = request.getSession();
				session.setAttribute("msg", "Data Deleted Successfully");
				response.sendRedirect("allLeads.jsp");
			}else {
				response.sendRedirect("allLeads.jsp");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
