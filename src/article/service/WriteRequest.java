package article.service;
/*
 * 게시글 쓰기에 필요한 데이터를 제공
 * 
 * 즉, form 에서 받은 내용을 저장하는 class
 * 
 * 
 * */

import java.util.Map;

import article.model.Writer;

public class WriteRequest {
	private Writer writer;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	//제목이 null 일 경우, validation check 에서 검사
	public void validate(Map<String,Boolean>errors){
		if(title == null||title.trim().isEmpty()){
			errors.put("title",Boolean.TRUE);
			
		}
	}
	
}
