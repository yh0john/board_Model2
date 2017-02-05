package jdbc;
/*
 * 반복적인 jdbc 자원 반납 코드를 줄이기 위한 class  
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	public static void close(Connection conn){
		if(conn !=null){
			try{
				conn.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt !=null){
			try{
				stmt.close();
			}catch(SQLException e){
				
			}
		}
	}
	public static void close(ResultSet rs){
		if(rs != null ){
			try{
				rs.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	public static void rollback(Connection conn){
		if(conn!=null){
			try{
				conn.rollback();
			}catch(SQLException e){
				
			}
		}
	}

	
}
