package article.dao;

import java.sql.Connection;
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
			//TODO Article insert 문 추가하기!
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
		}
		return article;
	}
	
	private Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
}
