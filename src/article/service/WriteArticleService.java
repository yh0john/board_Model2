package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

/*
 * WriteRequest 와 ArticleDao,ArticleContentDao 연결 class
 * 직접적인 작업 수행 class
 * 
 * */
public class WriteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest req){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(conn, article);
			
			//Insert return 값을 활용한 validation check 
			//wow
			if(savedArticle ==null){
				throw new RuntimeException("fail to insert article");
			}
			ArticleContent content = new ArticleContent(savedArticle.getNumber(),req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);
			
			//Insert return 값을 활용한 validation check
			//null일 경우 insert fail
			if(savedContent ==null){
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			
			JdbcUtil.close(conn);
		}
		return null;
		
	}
	
	private Article toArticle(WriteRequest req){
		Date date =  new Date();
		return new Article(null,req.getWriter(),req.getTitle(),date,date,0);
	}
}
