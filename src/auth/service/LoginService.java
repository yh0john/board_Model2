package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

/*
 * 로그인을 처리 할 Service class. member 객체를 활용한다.
 */
public class LoginService {
	private MemberDao memberDao = new MemberDao();
	
	public User login(String id,String password){
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, id);
			if(member == null){
				throw new LoginFailException();
			}
			if(!member.matchPassword(password)){
				throw new LoginFailException();
			}
			return new User(id,password);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
