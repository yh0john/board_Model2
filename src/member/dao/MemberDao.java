package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn,String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			Member member = null;
			
			if(rs.next()){
				member = new Member(rs.getString("memberid"),rs.getString("name"),rs.getString("password"),toDate(rs.getTimestamp("regDate")));
			}
			return member;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date){
		return date == null ? null : new Date(date.getTime());
	}
	
	public void insert(Connection conn,Member mem) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("insert into member values(?,?,?,?)")){
			pstmt.setString(1, mem.getMemberid());
			pstmt.setString(2, mem.getUsername());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			
		}
	}
	
	public void update(Connection conn,String id){
		
	}
}
