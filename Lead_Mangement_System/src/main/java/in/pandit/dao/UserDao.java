package in.pandit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.pandit.model.User;
import in.pandit.persistance.DatabaseConnection;

public class UserDao {
	/* **********************************************************
	 * ****** CREATED FINAL QUERIES FOR SECURITY OF SYSTEM ******
	 * **********************************************************
	 */
//	INSERT QUERIES
	private static final String ADD_USER_INFORMATION = "INSERT INTO users(email,gender,isadmin,issuperadmin,mobile,name,password,user_id,company_id) VALUES(?,?,?,?,?,?,?,?,?)";
	
//	SELECT QUERIES
	public static final String GET_USER_BY_USER_AND_COMPANY_ID = "SELECT * FROM users WHERE user_id=? AND company_id=?";
	public static final String GET_USER_BY_USER_ID = "SELECT * FROM users WHERE user_id=?";
	public static final String GET_COMPANY_BY_COMPANY_ID_AND_EMAIL = "SELECT * FROM users WHERE company_id=? AND email=?";
	public static final String GET_ALL_EMPLOYEE_BY_COMPANY_ID = "SELECT * FROM users WHERE company_id=?";
	public static final String GET_ALL_USER_BY_COMPANY_ID_AND_OFFSET_AND_LIMIT = "SELECT * FROM users WHERE company_id=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	public static final String LOGIN_USER = "SELECT * FROM users WHERE email=? AND password=?";
	
//	SELECT COUNT QUERIES
	public static final String TOTAL_EMPLOYEE_COUNT_OF_COMPANY_USING_COMPANY_ID = "SELECT COUNT(id) FROM users WHERE company_id=?";
	public static final String TOTAL_USER_COUNT_OF_COMPANY_USING_COMPANY_ID = "SELECT COUNT(id) FROM users WHERE company_id=?";
	public static final String TOTAL_COUNT = "SELECT COUNT(id) FROM users WHERE isadmin=? AND issuperadmin=?";
	
//	FOR SUPERADMIN SELECT QUERIES 
	public static final String GET_USER_BY_ISADMIN_AND_ISSUPERADMIN = "SELECT * FROM users WHERE isadmin=? AND issuperadmin=?";
	
	
//	UPDATE QUERIES
	private static final String UPDATE_USER_INFORMATION = "UPDATE users SET name=?,gender=?,mobile=? WHERE user_id=?";
	private static final String UPDATE_USER = "UPDATE users SET name=?,gender=?,mobile=?,password=? WHERE user_id=?";
	private static final String UPDATE_ADMIN_INFORMATION = "UPDATE users SET name=?,password=?,mobile=? WHERE user_id=?";
	
	
	private static PreparedStatement pst = null;
	private static Connection con = DatabaseConnection.getConnection();
	
	/* *************************************************
	 * ****** GET USER BY USER_ID AND COMPANY_ID *******
	 * *************************************************
	 */
	public static User getUserByUserAndCompanyId(String userId,String companyId) {
		User user = null;
		try {
			pst = con.prepareStatement(GET_USER_BY_USER_AND_COMPANY_ID);
			pst.setString(1, userId);
			pst.setString(2, companyId);
			ResultSet rst = pst.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static List<User> getUserByLimitAndCompanyId(String companyId,int limit , int offset ) {
		List<User> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_USER_BY_COMPANY_ID_AND_OFFSET_AND_LIMIT);
			pst.setString(1, companyId);
			pst.setInt(2, limit);
			pst.setInt(3, offset);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				User user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getUsersCountUsingCompanyId(String companyId) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_USER_COUNT_OF_COMPANY_USING_COMPANY_ID);
			pst.setString(1, companyId);
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
	public static int getUserCount(String isadmin , String issuperadmin) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_COUNT);
			pst.setString(1, isadmin);
			pst.setString(2, issuperadmin);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				count = rst.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static User getUserByUserId(String userId) {
		User user = null;
		try {
			pst = con.prepareStatement(GET_USER_BY_USER_ID);
			pst.setString(1, userId);
			ResultSet rst = pst.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* *************************************************
	 * ****** GET COMPANY BY COMPANY_ID AND EMAIL ******
	 * *************************************************
	 */
	public static User getCompanyByCompanyIdAndEmail(String companyEmail,String companyId) {
		User user = null;
		try {
			pst = con.prepareStatement(GET_COMPANY_BY_COMPANY_ID_AND_EMAIL);
			pst.setString(1, companyId);
			pst.setString(2, companyEmail);
			ResultSet rst = pst.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* *************************************************
	 * ****** GET COMPANY BY COMPANY_ID AND EMAIL ******
	 * *************************************************
	 */
	public static User loginUser(String email,String password) {
		User user = null;
		try {
			pst = con.prepareStatement(LOGIN_USER);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setIsSuperAdmin(rst.getString("issuperadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setGender(rst.getString("gender"));
				user.setMobile(rst.getString("mobile"));
				user.setUserId(rst.getString("user_id"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* ********************************************
	 * ****** GET ALL EMPLOYEE BY COMPANY_ID ******
	 * ********************************************
	 */
	public static List<User> getAllEmployeeByCompanyId(String companyId) {
		List<User> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_EMPLOYEE_BY_COMPANY_ID);
			pst.setString(1, companyId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				User user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<User> getAllEmployeeByIsadminAndIssuperadmin(String isadmin, String issuperadmin) {
		List<User> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_USER_BY_ISADMIN_AND_ISSUPERADMIN);
			pst.setString(1, isadmin);
			pst.setString(2, issuperadmin);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				User user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setMobile(rst.getString("mobile"));
				user.setGender(rst.getString("gender"));
				user.setUserId(rst.getString("user_id"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/* *************************************************************
	 * ****** GET EMPLOYEE COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *************************************************************
	 * */
	public static int getEmployeeCountUsingCompanyId(String companyId) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_EMPLOYEE_COUNT_OF_COMPANY_USING_COMPANY_ID);
			pst.setString(1, companyId);
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
	
	/* **************************************************
	 * ****** GET ADMIN(COMPANY) INFORMATION ************
	 * **************************************************
	 * */
	public static User getCompanyInfo(String userId) {
		User user = null;
		try {
			pst = con.prepareStatement("SELECT * FROM users WHERE user_id=?");
			pst.setString(1, userId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setMobile(rst.getString("mobile"));
				user.setUserId(rst.getString("user_id"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	
	/* **************************************************
	 * ****** GET USER INFO INFORMATION ************
	 * **************************************************
	 * */
	public static User getUserInfo(String userId,String companyId) {
		User user = null;
		try {
			pst = con.prepareStatement("SELECT * FROM users WHERE user_id=? AND company_id=?");
			pst.setString(1, userId);
			pst.setString(2, companyId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setMobile(rst.getString("mobile"));
				user.setUserId(rst.getString("user_id"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	
	/* ***********************************
	 * ****** DELETE USER BY USER ID *****
	 * ***********************************
	 * */
	public static int deleteUserByUserId(String userId) {
		try {
			pst = con.prepareStatement("DELETE FROM users WHERE user_id=?");
			pst.setString(1, userId);
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/* *********************
	 * ****** ADD USER *****
	 * *********************
	 * */
	
	public static boolean addUser(User user) {
		try {
			String userId = Long.toString(System.currentTimeMillis()+Integer.MAX_VALUE);
			
			pst = con.prepareStatement(ADD_USER_INFORMATION);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getGender());
			pst.setString(3, user.getIsAdmin());
			pst.setString(4, user.getIsSuperAdmin());
			pst.setString(5, user.getMobile());
			pst.setString(6, user.getName());
			pst.setString(7, user.getPassword());
			pst.setString(8, userId);
			pst.setString(9, user.getCompanyId());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updateUserInformation(User user) {
		try {
			pst = con.prepareStatement(UPDATE_USER_INFORMATION);
			pst.setString(1, user.getName());
			pst.setString(2, user.getGender());
			pst.setString(3, user.getMobile());
			pst.setString(4, user.getUserId());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updateUser(User user) {
		System.out.println("Gender = "+user.getGender());
		try {
			pst = con.prepareStatement(UPDATE_USER);
			pst.setString(1, user.getName());
			pst.setString(2, user.getGender());
			pst.setString(3, user.getMobile());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getUserId());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updateAdminInformation(User user) {
		try {
			pst = con.prepareStatement(UPDATE_ADMIN_INFORMATION);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getMobile());
			pst.setString(4, user.getUserId());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
