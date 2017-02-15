package board.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadServlet extends HttpServlet{
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		
		res.setCharacterEncoding("text/html; charset=utf-8");
		
		PrintWriter writer = res.getWriter();
		writer.println("<html><body>");
		
		String contentType = req.getContentType();
		
		//getContentType을 이용해서 multipart/form-data 인지 확인한다.
		if(contentType != null && contentType.toLowerCase().startsWith("multipart/")){
			printPartInfo(req,writer);
		}else{
			writer.println("multipart가 아님");
		}
		writer.println("</body></html>");
		
	}
	
	//multipart일 경우, getPart()메서드를 이용해서 part를 구한다.
	//Content-Disposition 헤더가 filename= 을 포함하면 파일로 처리
	private void printPartInfo(HttpServletRequest req,PrintWriter writer) throws ServletException,IOException{
		Collection<Part> parts = req.getParts();
		for(Part part:parts){
			writer.println("<br/> name ="+part.getName());
			String contentType = part.getContentType();
			writer.println("<br/> contentType="+contentType);
			
			//헤더가 file name=을 포함할 경우 파일로 처리
			if(part.getHeader("Content-Dispositon").contains("filename=")){
				writer.println("<br/> size = "+part.getSize());
				String fileName = part.getName();
				if(part.getSize()>0){
					part.write("/Users/test/temp/"+fileName);
					part.delete();
				}
			}else{
				//filename= 이 아닐 경우 파라미터로 처리
				String value = req.getParameter(part.getName());
				writer.println("<br/> value = "+value);
			}
			
			writer.println("<br/>");
			
		}
	}
}
