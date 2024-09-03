package in.pandit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.pandit.model.Lead;
import in.pandit.persistance.DatabaseConnection;

public class LeadDao {
	private static final String GET_LEAD_BY_ID = "SELECT * FROM leads WHERE id=?";
	private static final String GET_LEAD_BY_EMAIL = "SELECT * FROM leads WHERE email=?";
	private static final String GET_LEAD_BY_MOBILE = "SELECT * FROM leads WHERE mobile=?";
	private static final String GET_ALL_LEAD_BY_CURRENT_OWNER = "SELECT * FROM leads WHERE currentowner=?";
	private static final String GET_ALL_LEAD_BY_OWNER = "SELECT * FROM leads WHERE owner=?";
	private static final String DETETE_LEAD_BY_LEAD_ID="DELETE FROM leads WHERE id=?";
	private static final String TOTAL_LEADS_COUNT_OF_USER_USING_ID_AND_STATUS = "SELECT COUNT(id) FROM leads WHERE status=?";
	private static final String TOTAL_LEADS = "SELECT COUNT(id) FROM leads";
	private static final String TOTAL_LEADS_COUNT_SOURCE = "SELECT COUNT(id) FROM leads WHERE source=?";
	private static final String TOTAL_LEADS_COUNT_SOURCE_FACEBOOK_OR_GOOGLE = "SELECT COUNT(id) FROM leads WHERE source=? OR source=?";
	private static final String TOTAL_LEADS_COUNT_NEW_LEADS = "SELECT COUNT(id) FROM leads WHERE date::date = CURRENT_DATE";
	
	private static final String GET_ALL_LEADS_BY_OFFSET_AND_LIMIT = "SELECT * FROM leads ORDER BY id DESC LIMIT ? OFFSET ? ";
	private static final String GET_ALL_LEADS_BY_OFFSET_AND_LIMIT_AND_CURRENTOWNER = "SELECT * FROM leads WHERE currentowner=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	
	
	private static final String INSERT_LEAD = "INSERT INTO leads(name,email,address,mobile,source,date,time,owner,currentowner,status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_LEAD = "UPDATE leads SET name = ?, email = ?, address = ?, mobile = ?, source = ?, date = ?, owner = ?, currentOwner = ?, status = ? WHERE id=?";
	
	private static final String UPDATE_LEAD_CURRENT_OWNER_EMAIL_BY_CURRENT_OWNER_EMAIL = "UPDATE leads SET currentowner = ? WHERE currentowner = ?";
	private static final String UPDATE_LEAD_OWNER_EMAIL_BY_OWNER_EMAIL = "UPDATE leads SET owner = ? WHERE owner = ?";
	
	private static Connection con = DatabaseConnection.getConnection();
	private static PreparedStatement pst = null;
	
	
	public static int insertLead(Lead lead) {
		int done = 0;
		try {
			pst = con.prepareStatement(INSERT_LEAD);
			pst.setString(1, lead.getName());
			pst.setString(2, lead.getEmail());
			pst.setString(3, lead.getAddress());
			pst.setString(4, lead.getMobile());
			pst.setString(5, lead.getSource());
			pst.setString(6,lead.getDate());
			pst.setString(7, lead.getTime());
			pst.setString(8, lead.getOwner());
			pst.setString(9, lead.getCurrentowner());
			pst.setString(10, lead.getStatus());
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	public static int updateLead(Lead lead) {
		int done = 0;
		try {
			pst = con.prepareStatement(UPDATE_LEAD);
			pst.setString(1, lead.getName());
			pst.setString(2, lead.getEmail());
			pst.setString(3, lead.getAddress());
			pst.setString(4, lead.getMobile());
			pst.setString(5, lead.getSource());
			pst.setString(6,lead.getDate());
			pst.setString(7, lead.getOwner());
			pst.setString(8, lead.getCurrentowner());
			pst.setString(9, lead.getStatus());
			pst.setInt(10, lead.getId());
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	
	public static Lead getLeadById(int leadId) {
		Lead lead = null;
		try {
			pst = con.prepareStatement(GET_LEAD_BY_ID);
			pst.setInt(1, leadId);
			ResultSet rst=  pst.executeQuery();
			lead = new Lead();
			while(rst.next()) {
				lead.setId(rst.getInt("id"));
				lead.setAddress(rst.getString("address"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				return lead;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lead;
	}
	public static Lead getLeadByEmail(String leadEmail) {
		Lead lead = null;
		try {
			pst = con.prepareStatement(GET_LEAD_BY_EMAIL);
			pst.setString(1, leadEmail);
			ResultSet rst=  pst.executeQuery();
			lead = new Lead();
			while(rst.next()) {
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				return lead;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lead;
	}
	public static List<Lead> getAllLeadByEmail(String leadEmail) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_LEAD_BY_EMAIL);
			pst.setString(1, leadEmail);
			ResultSet rst=  pst.executeQuery();
			while(rst.next()) {
				Lead lead = new Lead();
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Lead getLeadByMobile(String mobile) {
		Lead lead = null;
		try {
			pst = con.prepareStatement(GET_LEAD_BY_MOBILE);
			pst.setString(1, mobile);
			ResultSet rst=  pst.executeQuery();
			lead = new Lead();
			while(rst.next()) {
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				return lead;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lead;
	}
	public static List<Lead> getAllLeadByMobile(String mobile) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_LEAD_BY_MOBILE);
			pst.setString(1, mobile);
			ResultSet rst=  pst.executeQuery();
			while(rst.next()) {
				Lead lead = new Lead();
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Lead> getAllLeadByCurrentOwner(String currentOwner) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_LEAD_BY_CURRENT_OWNER);
			pst.setString(1, currentOwner);
			ResultSet rst=  pst.executeQuery();
			while(rst.next()) {
				Lead lead = new Lead();
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Lead> getAllLeadByOwner(String Owner) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_LEAD_BY_OWNER);
			pst.setString(1, Owner);
			ResultSet rst=  pst.executeQuery();
			while(rst.next()) {
				Lead lead = new Lead();
				lead.setAddress(rst.getString("id"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setEmail(rst.getString("email"));
				lead.setId(rst.getInt("id"));
				lead.setMobile(rst.getString("mobile"));
				lead.setName(rst.getString("name"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int deleteLeadByLeadId(int leadid) {
		int done = 0;
		try {
			int a = CommentDao.deleteCommentByLeadId(leadid);
			System.out.println(a);
			pst =con.prepareStatement(DETETE_LEAD_BY_LEAD_ID);
			pst.setInt(1, leadid);
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	/* ****************************
	 * ****** GET LEAD COUNT ******
	 * ****************************
	 * */
	public static int getTotalLeadsCount() {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_LEADS);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	/* ****************************
	 * ****** GET LEAD COUNT ******
	 * ****************************
	 * */
//	BY ADDRESS, NAME , EMAIL , MOBILE , ID ,STATUS , OWNER
	
	public static int getLeadsCount(String query, String countBy) {
		int count = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, countBy);
			ResultSet rst = pst.executeQuery();
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
	public static int getLeadsCountUsingUserIdAndStatus(String status) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_LEADS_COUNT_OF_USER_USING_ID_AND_STATUS);
			pst.setString(1, status);
			ResultSet rst = pst.executeQuery();
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
	public static int getLeadsCountUsingSource(String source) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_LEADS_COUNT_SOURCE);
			pst.setString(1, source);
			ResultSet rst = pst.executeQuery();
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
	public static int getLeadsCountUsingSourceFacebookOrGoogle() {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_LEADS_COUNT_SOURCE_FACEBOOK_OR_GOOGLE);
			pst.setString(1, "facebook");
			pst.setString(2, "google");
			ResultSet rst = pst.executeQuery();
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
	public static int getLeadsCountNewLeads() {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_LEADS_COUNT_NEW_LEADS);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	/* ***********************************************
	 * ****** GET ALL LEADS BY LIMIT AND OFFSET ******
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByLimit(int limit, int offset) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_LEADS_BY_OFFSET_AND_LIMIT);
			pst.setInt(1, limit);
			pst.setInt(2, offset);
			ResultSet rst = pst.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/* ***********************************************
	 * ****** GET ALL LEADS BY LIMIT AND OFFSET ******
	 * ***********************************************
	 * */
	public static List<Lead> getAllLeadsByLimitAndCurrentOwener(int limit, int offset,String currentOwner) {
		List<Lead> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_LEADS_BY_OFFSET_AND_LIMIT_AND_CURRENTOWNER);
			pst.setString(1, currentOwner);
			pst.setInt(2, limit);
			pst.setInt(3, offset);
			ResultSet rst = pst.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/*
	 * **************************************************************
	 * ************* SEARCH METHODS *********************************
	 * **************************************************************
	 * */
//	GET LEADS BY ADDRESS
//	GET LEADS BY NAME
//	GET LEADS BY ID
//	GET LEADS BY MOBILE
//	GET LEADS BY STATUS
//	GET LEADS BY OWNER
//	GET LEADS BY EMAIL
	public static final String SEARCH_LEADS_BY_ID_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE id=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_EMAIL_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE email ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_ADDRESS_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE address ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_MOBILE_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE mobile ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_STATUS_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE status ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_OWNER_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE owner ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String SEARCH_LEADS_BY_NAME_OFFSET_AND_LIMIT = "SELECT * FROM leads WHERE name ILIKE ? ORDER BY id DESC LIMIT ? OFFSET ? ";
	
	
	public static List<Lead> searchLead(String searchBy , String search,int limit, int offset){
		LeadDao leadDao = new LeadDao();
		List<Lead> list = new ArrayList<>();
		if(searchBy.equals("id")){
			try{
				int id = Integer.parseInt(search);
				pst = con.prepareStatement(SEARCH_LEADS_BY_ID_OFFSET_AND_LIMIT);
				pst.setInt(1, id);
				pst.setInt(2, limit);
				pst.setInt(3, offset);
				ResultSet rst = pst.executeQuery();
				while(rst.next()){
					Lead lead = new Lead();
					lead.setId(rst.getInt("id"));
					lead.setName(rst.getString("name"));
					lead.setEmail(rst.getString("email"));
					lead.setAddress(rst.getString("address"));
					lead.setCurrentowner(rst.getString("currentowner"));
					lead.setDate(rst.getString("date"));
					lead.setMobile(rst.getString("mobile"));
					lead.setOwner(rst.getString("owner"));
					lead.setSource(rst.getString("source"));
					lead.setStatus(rst.getString("status"));
					lead.setTime(rst.getString("time"));
					list.add(lead);
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
			}
		}else if(searchBy.equals("email")){
			System.out.println("email");
			list = leadDao.search(SEARCH_LEADS_BY_EMAIL_OFFSET_AND_LIMIT, search, limit, offset);
		}else if(searchBy.equals("address")){
			System.out.println("address");
			list = leadDao.search(SEARCH_LEADS_BY_ADDRESS_OFFSET_AND_LIMIT, search, limit, offset);
		}else if(searchBy.equals("name")){
			System.out.println("name");
			list = leadDao.search(SEARCH_LEADS_BY_NAME_OFFSET_AND_LIMIT, search, limit, offset);
		}else if(searchBy.equals("mobile")){
			System.out.println("mobile");
			list = leadDao.search(SEARCH_LEADS_BY_MOBILE_OFFSET_AND_LIMIT, search, limit, offset);
		}else if(searchBy.equals("owner")){
			System.out.println("name");
			list = leadDao.search(SEARCH_LEADS_BY_OWNER_OFFSET_AND_LIMIT, search, limit, offset);
		}else if(searchBy.equals("status")){
			System.out.println("status");
			list = leadDao.search(SEARCH_LEADS_BY_STATUS_OFFSET_AND_LIMIT, search, limit, offset);
		}
		return list;
	}
	public List<Lead> search(String query , String search,int limit, int offset){
		System.out.println(search);
		List<Lead> list = new ArrayList<>();
		try{
			pst = con.prepareStatement(query);
			pst.setString(1, "%"+search+"%");
			pst.setInt(2, limit);
			pst.setInt(3, offset);
			ResultSet rst = pst.executeQuery();
			while(rst.next()){
				Lead lead = new Lead();
				lead.setId(rst.getInt("id"));
				lead.setName(rst.getString("name"));
				lead.setEmail(rst.getString("email"));
				lead.setAddress(rst.getString("address"));
				lead.setCurrentowner(rst.getString("currentowner"));
				lead.setDate(rst.getString("date"));
				lead.setMobile(rst.getString("mobile"));
				lead.setOwner(rst.getString("owner"));
				lead.setSource(rst.getString("source"));
				lead.setStatus(rst.getString("status"));
				lead.setTime(rst.getString("time"));
				list.add(lead);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return list;
	}
	
	
	public static boolean validateEmail(String email) {
		try {
			Lead lead = LeadDao.getLeadByEmail(email);
			if(lead.getName() == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean validateMobile(String mobile) {
		try {
			Lead lead = LeadDao.getLeadByMobile(mobile);
			if(lead.getName() == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public static int updateCurrentOwnerEmail(String updateEmail,String currentEmail) {
		int updateCurrentOwner = 0;
		try {
			pst = con.prepareStatement(UPDATE_LEAD_CURRENT_OWNER_EMAIL_BY_CURRENT_OWNER_EMAIL);
			pst.setString(1, updateEmail);
			pst.setString(2,currentEmail);
			updateCurrentOwner = pst.executeUpdate();
			return updateCurrentOwner;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCurrentOwner;
	}
	public static int updateOwnerEmail(String updateEmail,String currentEmail) {
		int updateOwner = 0;
		try {
			pst = con.prepareStatement(UPDATE_LEAD_OWNER_EMAIL_BY_OWNER_EMAIL);
			pst.setString(1, updateEmail);
			pst.setString(2, currentEmail);
			updateOwner = pst.executeUpdate();
			return updateOwner;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateOwner;
	}
	
}
