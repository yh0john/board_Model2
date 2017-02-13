package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		/*TODO ModifyHandler 구현은 아래와 같다.(조금 복잡함)
		 * GET 방식 - 수정할 게시글 데이터를 읽어와 폼에 보여준다.
		 * POST 방식 - 전송한 요청 파라미터를 이용해서 게시글을 수정한다. 파라미터 값이 잘못 된 경우 전송한 데이터를 이용해서 폼을 다시 보여준다.
		*/
		return null;
	}

}
