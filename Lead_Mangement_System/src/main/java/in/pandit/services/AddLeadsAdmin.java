package in.pandit.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.pandit.dao.LeadDao;
import in.pandit.helper.CookiesHelper;
import in.pandit.model.Lead;
import in.pandit.model.User;


@WebServlet("/AddLeadsAdmin")
public class AddLeadsAdmin extends HttpServlet {
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
		String priority = request.getParameter("priority");
		
		User userCookie = CookiesHelper.getUserCookies(request, "user");
		
		try {
			Lead lead = new Lead();
			lead.setName(name);
			lead.setEmail(email);
			lead.setAddress(address);
			lead.setMobile(mobile);
			lead.setSource(source);
			lead.setTime(time);
			lead.setDate(date);
			lead.setOwner(owner);
			lead.setCurrentowner(currentOwner);
			lead.setStatus(status);
			lead.setPriority(priority);
//			both parameters are same because , we are calling this method for  company add lead , thats why companyId and userId will we same
			int insert = LeadDao.insertLeads(lead,userCookie.getUserId(),userCookie.getUserId());
		if(insert > 0) {
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Lead added successfully');");
			pw.println("</script>");
			RequestDispatcher rd = request.getRequestDispatcher("allLeads.jsp");
			rd.include(request, response);
		}
		else {
			pw.print("Something wrong with database");
		}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
