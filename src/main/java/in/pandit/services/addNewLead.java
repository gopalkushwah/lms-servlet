package in.pandit.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.pandit.dao.LeadDao;
import in.pandit.model.Lead;


@WebServlet("/addNewLead")
public class addNewLead extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String source = request.getParameter("source").toLowerCase();
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String owner = request.getParameter("owner");
		String currentOwner = request.getParameter("currentOwner");
		String status = request.getParameter("status");
		
		HttpSession session = request.getSession();
		Lead leadByMobile = LeadDao.getLeadByMobile(mobile);
		if(leadByMobile.getName()==null){
			Lead leadByEmail = LeadDao.getLeadByEmail(email);
			if(leadByEmail.getName()==null){
				Lead lead = new Lead();
				lead.setAddress(address);
				lead.setCurrentowner(currentOwner);
				lead.setDate(date);
				lead.setEmail(email);
				lead.setMobile(mobile);
				lead.setName(name);
				lead.setOwner(owner);
				lead.setSource(source);
				lead.setStatus(status);
				lead.setTime(time);
				int insert = LeadDao.insertLead(lead);
				if(insert>0) {
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Lead added successfully');");
					pw.println("</script>");
					RequestDispatcher rd = request.getRequestDispatcher("allUserLeads.jsp");
					rd.include(request, response);
				}else {
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Something went wrong! try again');");
					pw.println("</script>");
					session.setAttribute("already-lead", "Something went wrong! try again");
					response.sendRedirect("dashboard.jsp");
				}
			}else {
				session.setAttribute("already-lead", "Lead already available with same email!");
				response.sendRedirect("dashboard.jsp");
			}
		}else {
			session.setAttribute("already-lead", "Lead already available with same mobile!");
			response.sendRedirect("dashboard.jsp");
		}
	}

}
