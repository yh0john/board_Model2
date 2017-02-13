package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//TODO GET 방식일 경우, 양식 리턴. POST 방식 일 경우 삭제 후 delete Success 페이지 안내
		return null;
	}

}
