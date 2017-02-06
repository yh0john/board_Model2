package auth.service;
/*
 * 인증에 성공한 사용자의 정보를 담는 class
 * 
 * User 객체는 session에 저장한다.
 * 
 */

public class User {
	private String id;
	private String name;
	
	
	public User(String id,String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
