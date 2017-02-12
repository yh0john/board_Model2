package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler{

	private static final String FORM_VIEW ="/WEB-INF/view/newArticleForm.jsp";
	private WriteArticleService writeArticleSrv = new WriteArticleService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req,res);
		}else{
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req,HttpServletResponse res){
		return FORM_VIEW;
		
	}
	
	private String processSubmit(HttpServletRequest req,HttpServletResponse res){
		Map<String,Boolean>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user,req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		int newArticleNo = writeArticleSrv.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/WEB-INF/view/newArticleSuccess.jsp";
	}
	
	private WriteRequest createWriteRequest(User user,HttpServletRequest req){
		return new WriteRequest(new Writer(user.getId(),user.getName()),req.getParameter("titie"),req.getParameter("content"));
	}

}
