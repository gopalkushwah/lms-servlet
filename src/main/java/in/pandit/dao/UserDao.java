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
	public static final String GET_USER_BY_USER_AND_COMPANY_ID = "SELECT * FROM users WHERE user_id=? AND company_id=?";
	public static final String GET_COMPANY_BY_COMPANY_ID_AND_EMAIL = "SELECT * FROM users WHERE company_id=? AND email=?";
	public static final String GET_ALL_EMPLOYEE_BY_COMPANY_ID = "SELECT * FROM users WHERE company_id=?";
	public static final String LOGIN_USER = "SELECT * FROM users WHERE email=? AND password=?";
	public static final String TOTAL_EMPLOYEE_COUNT_OF_COMPANY_USING_COMPANY_ID = "SELECT COUNT(id) FROM users WHERE company_id=?";
	
	private static PreparedStatement stmt = null;
	private static Connection connect = DatabaseConnection.getConnection();
	
	/* *************************************************
	 * ****** GET USER BY USER_ID AND COMPANY_ID *******
	 * *************************************************
	 */
	public static User getUserByUserAndCompanyId(String userId,String companyId) {
		User user = null;
		try {
			stmt = connect.prepareStatement(GET_USER_BY_USER_AND_COMPANY_ID);
			stmt.setString(1, userId);
			stmt.setString(2, companyId);
			ResultSet rst = stmt.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setCompanyEmail(rst.getString("company_email"));
				user.setMobile(rst.getString("mobile"));
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
			stmt = connect.prepareStatement(GET_COMPANY_BY_COMPANY_ID_AND_EMAIL);
			stmt.setString(1, companyId);
			stmt.setString(2, companyEmail);
			ResultSet rst = stmt.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setCompanyEmail(rst.getString("company_email"));
				user.setMobile(rst.getString("mobile"));
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
			stmt = connect.prepareStatement(LOGIN_USER);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()) {
				user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setCompanyEmail(rst.getString("company_email"));
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
			stmt = connect.prepareStatement(GET_ALL_EMPLOYEE_BY_COMPANY_ID);
			stmt.setString(1, companyId);
			ResultSet rst = stmt.executeQuery();
			while(rst.next()) {
				User user = new User();
				user.setName(rst.getString("name"));
				user.setEmail(rst.getString("email"));
				user.setId(rst.getInt("id"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setPassword(rst.getString("password"));
				user.setCompanyId(rst.getString("company_id"));
				user.setCompanyEmail(rst.getString("company_email"));
				user.setMobile(rst.getString("mobile"));
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
			stmt = connect.prepareStatement(TOTAL_EMPLOYEE_COUNT_OF_COMPANY_USING_COMPANY_ID);
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
	
	/* **************************************************
	 * ****** GET ADMIN(COMPANY) INFORMATION ************
	 * **************************************************
	 * */
	public static User getCompanyInfo(String userId) {
		User user = null;
		try {
			stmt = connect.prepareStatement("SELECT * FROM users WHERE user_id=?");
			stmt.setString(1, userId);
			ResultSet rst = stmt.executeQuery();
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
			stmt = connect.prepareStatement("SELECT * FROM users WHERE user_id=? AND company_id=?");
			stmt.setString(1, userId);
			stmt.setString(2, companyId);
			ResultSet rst = stmt.executeQuery();
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
			stmt = connect.prepareStatement("DELETE FROM users WHERE user_id=?");
			stmt.setString(1, userId);
			return stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
