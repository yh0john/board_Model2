package article.service;


/*
 * 삭제 할 article 정보를 담는 Class
 * */

public class DeleteRequest {
	private String userId;
	private int articleNumber;
	
	
	public DeleteRequest(String userId, int articleNumber) {
		super();
		this.userId = userId;
		this.articleNumber = articleNumber;
	}


	public String getUserId() {
		return userId;
	}


	public int getArticleNumber() {
		return articleNumber;
	}
	

}
