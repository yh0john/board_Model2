package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler  {
	public abstract String process(HttpServletRequest req,HttpServletResponse res) throws Exception;
}
