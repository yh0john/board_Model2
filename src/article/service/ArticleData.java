package article.service;

import article.model.Article;
import article.model.ArticleContent;

/*
 *Article 클래스와 ArticleContent 클래스를 저장하기 위한 중간 클래스
 *
 * 
 * */
public class ArticleData {
	private Article article;
	private ArticleContent content;
	
	public ArticleData(Article article, ArticleContent content) {
		super();
		this.article = article;
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public ArticleContent getContent() {
		return content;
	}
	
	
}
