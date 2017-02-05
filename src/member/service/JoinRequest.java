package member.service;

import java.util.Map;

/*
 *  회원 가입 form 내용을 저장하는 class
 */
public class JoinRequest {
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	//form 으로 입력 받은 값 validation check 
	//비밀번호 및 null 입력 check
	
	public boolean isPasswordEqualToConfirm(){
		return password != null && password.equals(confirmPassword);
	}
	
	public void validate(Map<String,Object> errors){
		checkEmpty(errors,id,"id");
		checkEmpty(errors,name,"name");
		checkEmpty(errors,password,"password");
		checkEmpty(errors,confirmPassword,"confirmPassword");
		if(!errors.containsKey("confirmPassword")){
			if(!isPasswordEqualToConfirm()){
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	
	private void checkEmpty(Map<String,Object> errors,String value,String parameterName){
		if(value == null || value.isEmpty()){
			errors.put(parameterName, Boolean.TRUE);
		}
	}

	
}
