package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

/*
 * Loigin 요청을 받아 처리하는 Handler class 
 * GET request가 오면 폼을 위한 뷰를 리턴
 * POST request 일 경우 LoginService를 이용해 로그인 처리
 */

public class LoginHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();

	
	//Http Method 처리
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req,res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		
		//parameter validation check
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(id == null ||id.isEmpty()){
			errors.put("id", Boolean.TRUE);
		}
		if(password == null || password.isEmpty()){
			errors.put("password", Boolean.TRUE);
		}
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		try{
			//user 객체 session 저장
			User user =loginService.login(id,password);
			req.getSession().setAttribute("authUser", user);
			res.sendRedirect(req.getContextPath()+"/index.jsp");
			return null;
		}catch(LoginFailException e){
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}
	
	private String trim(String str){
		return str == null ? null :str.trim();
	}

}
