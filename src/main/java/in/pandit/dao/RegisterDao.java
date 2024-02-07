package in.pandit.dao;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.pandit.model.User;
import in.pandit.persistance.DatabaseConnection;

public class RegisterDao {
	/* **********************************************************
	 * ****** CREATED FINAL QUERIES FOR SECURITY OF SYSTEM ******
	 * **********************************************************
	 */
	public static final String SELECT_MAX_ID = "SELECT max(id) AS id FROM users";
	public static final String INSERT_INTO_COMPANY = "INSERT INTO users(id,name,email,mobile,password,isadmin,issuperadmin,user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_INTO_USER = "INSERT INTO users(id,name,email,mobile,password,isadmin,issuperadmin,user_id,company_id,company_email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static PreparedStatement stmt = null;
	private static Connection connect = DatabaseConnection.getConnection();
	
	/* ******************************
	 * ****** INSERT COMPANY ********
	 * ******************************
	 */
	public static int insertCompany(User user,HttpServletRequest request) {
		int status = 0;
		try {
			String isCompany = "true";
			String isSuperAdmin = "false";
			
			int count = 0;
//			 Fetching max id from sign up table for auto increment id
			PreparedStatement st = connect.prepareStatement(SELECT_MAX_ID);
			HttpSession session = request.getSession();
			session.setAttribute("totalLeads", count);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				count = rs.getInt("id");
			}
			
			// GENERATING ID USING System.currentTimeMillis() method for unique id
			String userId = Long.toString(System.currentTimeMillis()+789856466);
			// Inserting data into data base
			stmt = connect.prepareStatement(INSERT_INTO_COMPANY);
			stmt.setInt(1, ++count);
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getMobile());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, isCompany);
			stmt.setString(7, isSuperAdmin);
			stmt.setString(8, userId);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	/* ******************************
	 * ****** INSERT EMPLOYEE *******
	 * ******************************
	 */
	public static int insertUser(User user,HttpServletRequest request) {
		int status = 0;
		try {
			String isCompany = "false";
			String isSuperAdmin = "false";
			
			int count = 0;
//			 Fetching max id from sign up table for auto increment id
			PreparedStatement st = connect.prepareStatement(SELECT_MAX_ID);
			HttpSession session = request.getSession();
			session.setAttribute("totalLeads", count);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				count = rs.getInt("id");
			}
			// GENERATING ID USING System.currentTimeMillis() method for unique id
			String userId = Long.toString(System.currentTimeMillis());
			// Inserting data into data base
			stmt = connect.prepareStatement(INSERT_INTO_USER);
			stmt.setInt(1, ++count);
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getMobile());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, isCompany);
			stmt.setString(7, isSuperAdmin);
			stmt.setString(8, userId);
			stmt.setString(9, user.getCompanyId());
			stmt.setString(10, user.getCompanyEmail());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
