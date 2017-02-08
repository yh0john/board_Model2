package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet{
	//<커맨드,핸들러인스턴스> 매핑 정보 저장
	private Map<String,CommandHandler> commandHandlerMap = new HashMap<>();
	
	public void init() throws ServletException{
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try(FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		}catch(IOException e){
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try{
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			}catch(ClassNotFoundException|InstantiationException|IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		process(req,res);
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		process(req,res);
	}
	
	private void process(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath())==0){
			command = command.substring(req.getContextPath().length());
		}
		//FIXME 로그인 시 handler null 값 check
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null){
			handler = new NullHandler();
		}
		String viewPage = null;
		try{
			viewPage = handler.process(req, res);
		}catch(Throwable e){
			throw new ServletException(e);
		}
		
		if(viewPage != null){
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
		}
	}
}
