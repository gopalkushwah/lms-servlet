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

import in.pandit.persistance.DatabaseConnection;


@WebServlet("/deleteAdminUsers")
public class deleteAdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("delete").trim();
		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pst = conn.prepareStatement("delete from users where email = ?");
			pst.setString(1, email);
			int check = pst.executeUpdate();
			if(check>0) {
				HttpSession session = request.getSession();
				session.setAttribute("msg", "Data Deleted Successfully");
				response.sendRedirect("allUsers.jsp");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
