package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	private String encoding;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, res);
		System.out.println("테스트 로그입니다.");
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
		 encoding = config.getServletContext().getInitParameter("encoding");
		
		if(encoding == null ||encoding.isEmpty()){
			encoding="UTF-8";
		}
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
