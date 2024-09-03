package in.pandit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.pandit.model.Lead;
import in.pandit.persistance.DatabaseConnection;

public class LeadDao {
//	for company
//	COUNT QUERIES
	public static final String TOTAL_LEADS = "SELECT COUNT(id) FROM leads";
	public static final String TOTAL_LEADS_COUNT_OF_COMPANY_USING_USER_ID = "SELECT COUNT(id) FROM leads WHERE user_id=?";
	public static final String TOTAL_LEADS_COUNT_OF_COMPANY_USING_CURRENT_OWNER = "SELECT COUNT(id) FROM leads WHERE currentowner=?";
	public static final String TOTAL_LEADS_COUNT_OF_COMPANY_USING_COMPANY_ID_AND_SOURCE = "SELECT COUNT(id) FROM leads WHERE company_id=? AND source=?";
	public static final String TOTAL_LEADS_COUNT_OF_COMPANY_USING_COMPANY_ID = "SELECT COUNT(id) FROM leads WHERE company_id=?";
	public static final String TOTAL_LEADS_COUNT_OF_USER_USING_USER_ID_AND_SOURCE = "SELECT COUNT(id) FROM leads WHERE user_id=? AND source=?";
	public static final String TOTAL_LEADS_COUNT_OF_USER_USING_USER_ID_AND_STATUS = "SELECT COUNT(id) FROM leads WHERE user_id=? AND status=?";
	
//	MAX QUERIES
	public static final String MAX_ID_OF_LEAD = "SELECT MAX(id) AS id FROM leads";
	
//	INSERT QUERIES
	public static final String INSERT_LEADS = "INSERT INTO leads(name,email,address,mobile,source,date,time,owner,currentowner,status,priority,company_id,user_id,leads_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//	SELECT QUERIES
	public static final String GET_ALL_LEADS_BY_COMPANY_ID = "SELECT * FROM leads WHERE company_id=?";
	public static final String GET_ALL_LEADS_BY_COMPANY_ID_AND_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE company_id=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String GET_ALL_LEADS_BY_USER_EMAIL_AND_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE currentowner=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String GET_ALL_LEADS_BY_USER_ID = "SELECT * FROM leads WHERE user_id=?";
	public static final String GET_LEADS_BY_LEAD_ID = "SELECT * FROM leads WHERE leads_id=?";
	public static final String GET_LEADS_BY_CURRENT_OWNER_AND_COMPANY_ID = "SELECT * FROM leads WHERE currentowner=? AND company_id=?";

//	SELECT QUERIES
	public static final String DELETE_LEAD_BY_LEAD_ID = "DELETE FROM leads WHERE leads_id=?";
	
	
	private static PreparedStatement stmt = null;
	private static Connection connect = DatabaseConnection.getConnection();
	
	
	/* ****************************
	 * ****** GET LEAD COUNT ******
	 * ****************************
	 * */
	public static int getTotalLeadsCount() {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	/* *********************************************************
	 * ****** GET LEAD COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *********************************************************
	 * */
	public static int getLeadsCountUsingUserId(String userId) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_COMPANY_USING_USER_ID);
			stmt.setString(1, userId);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	public static int getLeadsCountUsingCurrentOwner(String email) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_COMPANY_USING_CURRENT_OWNER);
			stmt.setString(1, email);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	/* *********************************************************
	 * ****** GET LEAD COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *********************************************************
	 * */
	public static int getLeadsCountUsingCompanyIdAndSource(String userId,String source) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_COMPANY_USING_COMPANY_ID_AND_SOURCE);
			stmt.setString(1, userId);
			stmt.setString(2, source);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	/* *******************************************
	 * ****** GET LEAD COUNT USING (COMPANY) ID **
	 * *******************************************
	 * */
	public static int getLeadsCountUsingCompanyId(String companyId) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_COMPANY_USING_COMPANY_ID);
			stmt.setString(1, companyId);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	/* *********************************************************
	 * ****** GET LEAD COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *********************************************************
	 * */
	public static int getLeadsCountUsingUserIdAndSource(String userId,String source) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_USER_USING_USER_ID_AND_SOURCE);
			stmt.setString(1, userId);
			stmt.setString(2, source);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	/* ********************************************************************
	 * ****** GET LEAD COUNT USING USER ID AND STATUS FOR ADMIN(COMPANY) **
	 * ********************************************************************
	 * */
	public static int getLeadsCountUsingUserIdAndStatus(String userId,String status) {
		int count = 0;
		try {
			stmt = connect.prepareStatement(TOTAL_LEADS_COUNT_OF_USER_USING_USER_ID_AND_STATUS);
			stmt.setString(1, userId);
			stmt.setString(2, status);
			ResultSet rst = stmt.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	/* *********************************************************
	 * ****** GET LEAD COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *********************************************************
	 * */
	public static int insertLeads(Lead lead,String companyId,String userId) {
		int isInserted = 0;
		
		try {
			String leadsId = Long.toString(System.currentTimeMillis()+ Integer.MAX_VALUE);
			stmt = connect.prepareStatement(INSERT_LEADS);
			stmt.setString(1, lead.getName());
			stmt.setString(2, lead.getEmail());
			stmt.setString(3, lead.getAddress());
			stmt.setString(4, lead.getMobile());
			stmt.setString(5, lead.getSource());
			stmt.setString(6, lead.getDate());
			stmt.setString(7, lead.getTime());
			stmt.setString(8, lead.getOwner());
			stmt.setString(9, lead.getCurrentowner());
			stmt.setString(10, lead.getStatus());
			stmt.setString(11, lead.getPriority());
			stmt.setString(12, companyId); 
			stmt.setString(13, userId); 
			stmt.setString(14, leadsId);
			isInserted = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isInserted;
	}
	
	/* ***********************************************
	 * ****** GET ALL LEADS USING ADMIN(COMPANY) ID **
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByCompanyId(String companyId) {
		List<Lead> list = new ArrayList<>();
		try {
			stmt = connect.prepareStatement(GET_ALL_LEADS_BY_COMPANY_ID);
			stmt.setString(1, companyId);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/* ***********************************************
	 * ****** GET ALL LEADS USING ADMIN(COMPANY) ID **
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByCompanyIdAndLimit(String companyId,int limit, int offset) {
		List<Lead> list = new ArrayList<>();
		try {
			stmt = connect.prepareStatement(GET_ALL_LEADS_BY_COMPANY_ID_AND_OFFSET_AND_LIMIT);
			stmt.setString(1, companyId);
			stmt.setInt(2, limit);
			stmt.setInt(3, offset);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/* ***********************************************
	 * ****** GET ALL LEADS USING ADMIN(COMPANY) ID **
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByCurrentOwenerIdAndLimit(String userEmail,int limit, int offset) {
		List<Lead> list = new ArrayList<>();
		try {
			stmt = connect.prepareStatement(GET_ALL_LEADS_BY_USER_EMAIL_AND_OFFSET_AND_LIMIT);
			stmt.setString(1, userEmail);
			stmt.setInt(2, limit);
			stmt.setInt(3, offset);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/* ***********************************************
	 * ****** GET ALL LEADS USING USER ID ************
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByUserId(String userId) {
		List<Lead> list = new ArrayList<>();
		try {
			stmt = connect.prepareStatement(GET_ALL_LEADS_BY_USER_ID);
			stmt.setString(1, userId);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/* **************************************
	 * ****** GET LEAD BY LEAD ID ***********
	 * **************************************
	 * */
	public static Lead getLeadsByLeadId(String leadId) {
		Lead lead = null;
		try {
			stmt = connect.prepareStatement(GET_LEADS_BY_LEAD_ID);
			stmt.setString(1, leadId);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()){
				lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lead;
	}
	
	/* ***********************************************************
	 * ****** GET LEAD BY CURRENT OWNER AND COMPANY ID ***********
	 * ***********************************************************
	 * */
	public static List<Lead> getLeadsByCurrentOwnerAndCompanyId(String currentOwner ,String companyId) {
		List<Lead> list = null;
		try {
			stmt = connect.prepareStatement(GET_LEADS_BY_CURRENT_OWNER_AND_COMPANY_ID);
			stmt.setString(1, currentOwner);
			stmt.setString(2, companyId);
			ResultSet rst = stmt.executeQuery();
			list = new ArrayList<>();
			while(rst.next()){
				Lead lead = new Lead();
				lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCompany_id(rst.getString("company_id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setLeads_id(rst.getString("leads_id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setPriority(rst.getString("priority"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				lead.setUser_id(rst.getString("user_id"));
				list.add(lead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/* ***********************************************
	 * ****** GET ALL LEADS USING ADMIN(COMPANY) ID **
	 * ***********************************************
	 * */
	public static int deleteLeadLeadsId(String leadsId) {
		int isDeleted=0; 
		try {
			stmt = connect.prepareStatement(DELETE_LEAD_BY_LEAD_ID);
			stmt.setString(1, leadsId);
			return  stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	
	
	
}
