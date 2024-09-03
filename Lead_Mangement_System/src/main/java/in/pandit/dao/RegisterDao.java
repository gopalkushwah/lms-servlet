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
	public static final String INSERT_INTO_COMPANY = "INSERT INTO users(name,email,mobile,password,isadmin,issuperadmin,user_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_INTO_USER = "INSERT INTO users(name,email,mobile,password,isadmin,issuperadmin,user_id,company_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
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
			
			// GENERATING ID USING System.currentTimeMillis() method for unique id
			String userId = Long.toString(System.currentTimeMillis()+789856466);
			// Inserting data into data base
			stmt = connect.prepareStatement(INSERT_INTO_COMPANY);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getMobile());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, isCompany);
			stmt.setString(6, isSuperAdmin);
			stmt.setString(7, userId);
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
			
			// GENERATING ID USING System.currentTimeMillis() method for unique id
			String userId = Long.toString(System.currentTimeMillis());
			// Inserting data into data base
			stmt = connect.prepareStatement(INSERT_INTO_USER);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getMobile());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, isCompany);
			stmt.setString(6, isSuperAdmin);
			stmt.setString(7, userId);
			stmt.setString(8, user.getCompanyId());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
