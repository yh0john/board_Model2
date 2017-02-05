package member.model;

import java.util.Date;

public class Member {
	private String memberid;
	private String username;
	private String password;
	private Date regDate;
	
	
	public Member(String memberid,String username,String password,Date regDate){
		this.memberid = memberid;
		this.username = username;
		this.password = password;
		this.regDate = regDate;
	}
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public boolean matchPassword(String pwd){
		return this.password.equals(pwd);
	}
}
