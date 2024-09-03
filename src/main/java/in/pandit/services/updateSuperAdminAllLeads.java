package in.pandit.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.LeadDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.Lead;

@WebServlet("/updateSuperAdminAllLeads")
public class updateSuperAdminAllLeads extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id").trim());
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String source = request.getParameter("source");
		String date = request.getParameter("date");
		String owner = request.getParameter("owner");
		String currentOwner = request.getParameter("currentOwner");
		String status = request.getParameter("status");
		HttpSession session = request.getSession();

		Lead lead = LeadDao.getLeadById(id);

		Lead leadCookie = CookiesHelper.getLeadCookies(request, "lead");

		if (!mobile.equals(leadCookie.getMobile())) {
			boolean isMobileAvailable = LeadDao.validateMobile(mobile);
			if (isMobileAvailable) {
				lead.setMobile(mobile);
			} else {
				session.setAttribute("mobileUpdateMsg", "Lead already available with same mobile!");
				response.sendRedirect("updateSuperAdminAllLeads.jsp?update="+id);
				return;
			}
		}

		if (!email.equals(leadCookie.getEmail())) {
			boolean isEmailAvailable = LeadDao.validateEmail(email);
			if (isEmailAvailable) {
				lead.setEmail(email);
			} else {
				session.setAttribute("emailUpdateMsg", "Lead already available with same email!");
				response.sendRedirect("updateSuperAdminAllLeads.jsp?update="+id);
				return;
			}
		}

		lead.setAddress(address);
		lead.setCurrentowner(currentOwner);
		lead.setDate(date);
		lead.setName(name);
		lead.setOwner(owner);
		lead.setSource(source);
		lead.setStatus(status);
		lead.setId(id);
		int insert = LeadDao.updateLead(lead);
		if (insert > 0) {
			System.out.println("data inserted");
			session.removeAttribute("updateMsg");
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Lead updated successfully');");
			pw.println("</script>");
			session.setAttribute("updateMsg", "Lead updated successfully");
			response.sendRedirect("allLeadsSuperAdmin.jsp");
		} else {
			System.out.println("Not inserted");
			session.removeAttribute("updateMsg");
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Something went wrong! try again');");
			pw.println("</script>");
			session.setAttribute("updateMsg", "Something went wrong! try again");
			response.sendRedirect("allLeadsSuperAdmin.jsp");
		}
	}

}
