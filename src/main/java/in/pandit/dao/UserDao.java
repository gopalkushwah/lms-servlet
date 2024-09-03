package in.pandit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.pandit.model.User;
import in.pandit.persistance.DatabaseConnection;

public class UserDao {
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
	private static final String GET_ALL_USER_LIMIT_OFFSET = "SELECT * FROM users WHERE isadmin=? AND issuperadmin=? ORDER BY id DESC LIMIT ? OFFSET ? ";
	private static final String GET_USER_BY_MOBILE = "SELECT * FROM users WHERE mobile=?";
	private static final String GET_NAME_BY_EMAIL = "SELECT name FROM users WHERE email=?";
	private static final String UPDATE_USER_INFORMATION = "UPDATE users SET name=?,email=?,gender=?,mobile=? WHERE id=?";
	private static final String UPDATE_USER_EMAIL = "UPDATE users SET email=? WHERE email=?";
	private static final String ADD_USER_INFORMATION = "INSERT INTO users(email,gender,isadmin,issuperadmin,mobile,name,password) VALUES(?,?,?,?,?,?,?)";
	public static final String TOTAL_COUNT = "SELECT COUNT(id) FROM users WHERE isadmin=? AND issuperadmin=?";
	public static final String LOGIN_USER = "SELECT * FROM users WHERE email=? AND password=?";
	public static final String TOTAL_USER_COUNT_USING_ISADMIN = "SELECT COUNT(id) FROM users WHERE isadmin=? AND issuperadmin=?";
	
	private static PreparedStatement pst = null;
	private static Connection con = DatabaseConnection.getConnection();
	public static User getUserByEmail(String email) {
		User user = null;
		try {
			pst = con.prepareStatement(GET_USER_BY_EMAIL);
			pst.setString(1, email);
			ResultSet rst = pst.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setId(rst.getInt("id"));
				user.setEmail(rst.getString("email"));
				user.setGender(rst.getString("gender"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setIsSuperAdmin(rst.getString("issuperadmin"));
				user.setMobile(rst.getString("mobile"));
				user.setName(rst.getString("name"));
				user.setPassword(rst.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static List<User> getAllUserByLimitAndOffset(int limit, int offset,String isAdmin , String isSuperAdmin) {
		List<User> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_ALL_USER_LIMIT_OFFSET);
			pst.setString(1, isAdmin);
			pst.setString(2, isSuperAdmin);
			pst.setInt(3, limit);
			pst.setInt(4, offset);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				User user = new User();
				user.setId(rst.getInt("id"));
				user.setEmail(rst.getString("email"));
				user.setGender(rst.getString("gender"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setIsSuperAdmin(rst.getString("issuperadmin"));
				user.setMobile(rst.getString("mobile"));
				user.setName(rst.getString("name"));
				user.setPassword(rst.getString("password"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
				user.setGender(rst.getString("gender"));
				user.setMobile(rst.getString("mobile"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static User getUserByMobile(String mobile) {
		User user = null;
		try {
			pst = con.prepareStatement(GET_USER_BY_MOBILE);
			pst.setString(1, mobile);
			ResultSet rst = pst.executeQuery();
			user = new User();
			while(rst.next()) {
				user.setId(rst.getInt("id"));
				user.setEmail(rst.getString("email"));
				user.setGender(rst.getString("gender"));
				user.setIsAdmin(rst.getString("isadmin"));
				user.setIsSuperAdmin(rst.getString("issuperadmin"));
				user.setMobile(rst.getString("mobile"));
				user.setName(rst.getString("name"));
				user.setPassword(rst.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static String getUserNameByEmail(String email) {
		try {
			pst = con.prepareStatement(GET_NAME_BY_EMAIL);
			pst.setString(1, email);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				return rst.getString("name");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	public static boolean updateUserInformation(User user) {
		try {
			pst = con.prepareStatement(UPDATE_USER_INFORMATION);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getGender());
			pst.setString(4, user.getMobile());
			pst.setInt(5, user.getId());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean updateUserEmail(String updateEmail,String currentEmail) {
		try {
			pst = con.prepareStatement(UPDATE_USER_EMAIL);
			pst.setString(1, updateEmail);
			pst.setString(2, currentEmail);
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean addUser(User user) {
		try {
			pst = con.prepareStatement(ADD_USER_INFORMATION);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getGender());
			pst.setString(3, user.getIsAdmin());
			pst.setString(4, user.getIsSuperAdmin());
			pst.setString(5, user.getMobile());
			pst.setString(6, user.getName());
			pst.setString(7, user.getPassword());
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/* *************************************************************
	 * ****** GET EMPLOYEE COUNT USING USER ID FOR ADMIN(COMPANY) **
	 * *************************************************************
	 * */
	public static int getUserCountUsingIsAdmin(String isAdmin,String isSuperAdmin) {
		int count = 0;
		try {
			pst = con.prepareStatement(TOTAL_USER_COUNT_USING_ISADMIN);
			pst.setString(1, isAdmin);
			pst.setString(2, isSuperAdmin);
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
}
