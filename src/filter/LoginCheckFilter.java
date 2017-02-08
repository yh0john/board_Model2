package filter;

import java.io.IOException;

/*
 * Login 여부 판별하는 class 
 * 회원 정보 수정 및 비밀번호 변경
 * */

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request= (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if(session.getAttribute("authUser")==null || session==null){
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do");
		}
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
