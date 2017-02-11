package article.dao;

import java.sql.Connection;

/*
 * 
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import article.model.Article;
import jdbc.JdbcUtil;

public class ArticleDao {
	public Article insert(Connection conn,Article article)throws SQLException{
		PreparedStatement  pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt) values(?,?,?,?,?,0)");
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4, toTimestamp(article.getRegDate()));
			pstmt.setTimestamp(5, toTimestamp(article.getModifiedDate()));
			
			//글쓰기 완료 후 최근 글 id 가져오기
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount >0){
				stmt = conn.createStatement();
				rs=stmt.executeQuery("select last_insert_id() from article");
				
				if(rs.next()){
					Integer newNum = rs.getInt(1);
					return new Article(newNum,article.getWriter(),article.getTitle(),article.getRegDate(),article.getModifiedDate(),0);
				}
				
			}
			
			return null;
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
		}
		
	}
	
	private Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
}
