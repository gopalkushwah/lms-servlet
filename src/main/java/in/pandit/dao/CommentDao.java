package in.pandit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.pandit.model.Comment;
import in.pandit.persistance.DatabaseConnection;

public class CommentDao {
	
	private static final String INSERT_COMMENT="INSERT INTO comments(leadid,userid,useremail,comment) VALUES(?,?,?,?)";
	private static final String UPDATE_COMMENT="UPDATE comments SET comment=? WHERE id=?";
	private static final String UPDATE_COMMENT_USER_EMAIL_BY_USER_EMAIL="UPDATE comments SET useremail = ? WHERE useremail = ?";
	private static final String GET_COMMENT_BY_COMMENT_ID="SELECT * FROM comments WHERE id=?";
	private static final String GET_All_COMMENT_BY_LEAD_ID="SELECT * FROM comments WHERE leadid=? ORDER BY creation_date DESC";
	private static final String GET_All_COMMENT_BY_USER_ID="SELECT * FROM comments WHERE userid=? ORDER BY creation_date DESC";
	private static final String DETETE_COMMENT_BY_LEAD_ID="DELETE FROM comments WHERE leadid=?";
	
	private static Connection con = DatabaseConnection.getConnection();
	private static PreparedStatement pst = null;
	
	public static int insertComment(Comment comment) {
		int done = 0;
		try {
			pst =con.prepareStatement(INSERT_COMMENT);
			pst.setInt(1, comment.getLeadid());
			pst.setInt(2, comment.getUserid());
			pst.setString(3, comment.getUseremail());
			pst.setString(4, comment.getComment());
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static int updateComment(Comment comment) {
		int done = 0;
		try {
			pst =con.prepareStatement(UPDATE_COMMENT);
			pst.setString(1, comment.getComment());
			pst.setInt(2, comment.getId());
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	public static int updateCommentUserEmail(String updateEmail,String currentEmail) {
		int done = 0;
		try {
			pst =con.prepareStatement(UPDATE_COMMENT_USER_EMAIL_BY_USER_EMAIL);
			pst.setString(1, updateEmail);
			pst.setString(2, currentEmail);
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	public static int deleteCommentByLeadId(int leadid) {
		int done = 0;
		try {
			pst =con.prepareStatement(DETETE_COMMENT_BY_LEAD_ID);
			pst.setInt(1, leadid);
			done = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public static List<Comment> getAllCommentByLeadId(int leadid) {
		List<Comment> list= new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_All_COMMENT_BY_LEAD_ID);
			pst.setInt(1, leadid);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				Comment comment = new Comment();
				comment.setId(rst.getInt("id"));
				comment.setLeadid(rst.getInt("leadid"));
				comment.setUserid(rst.getInt("userid"));
				comment.setUseremail(rst.getString("useremail"));
				comment.setComment(rst.getString("comment"));
				comment.setCreationDate(rst.getDate("creation_date"));
				comment.setUpdationDate(rst.getDate("updatation_date"));
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Comment> getAllCommentByUserId(int userId) {
		List<Comment> list= new ArrayList<>();
		try {
			pst = con.prepareStatement(GET_All_COMMENT_BY_USER_ID);
			pst.setInt(1, userId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				Comment comment = new Comment();
				comment.setId(rst.getInt("id"));
				comment.setLeadid(rst.getInt("leadid"));
				comment.setUserid(rst.getInt("userid"));
				comment.setUseremail(rst.getString("useremail"));
				comment.setComment(rst.getString("comment"));
				comment.setCreationDate(rst.getDate("creation_date"));
				comment.setUpdationDate(rst.getDate("updatation_date"));
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Comment getCommentByCommentId(int commentId) {
		Comment comment = null;
		try {
			pst = con.prepareStatement(GET_COMMENT_BY_COMMENT_ID);
			pst.setInt(1, commentId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				comment = new Comment();
				comment.setId(rst.getInt("id"));
				comment.setLeadid(rst.getInt("leadid"));
				comment.setUserid(rst.getInt("userid"));
				comment.setUseremail(rst.getString("useremail"));
				comment.setComment(rst.getString("comment"));
				comment.setCreationDate(rst.getDate("creation_date"));
				comment.setUpdationDate(rst.getDate("updatation_date"));
				return comment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment;
	}
	
}
