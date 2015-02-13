package org.home.gae.shopping.business;

import java.util.List;

import org.home.gae.shopping.model.Article;
import org.home.gae.shopping.persistence.ArticleDao;
import org.home.gae.shopping.persistence.ArticleDaoImpl;

public class ArticleManagementService {
	
	public void addArticle(String userId, String articleName, String amount) {
		ArticleDao articleDao = new ArticleDaoImpl();
		articleDao.add(userId, articleName, new Integer(amount));
	}

	public void deleteArticle(String id) {
		ArticleDao articleDao = new ArticleDaoImpl();
		articleDao.remove(Long.parseLong(id));	
	}
	
	public List<Article> getArticles(String userId) {
		ArticleDao articleDao = new ArticleDaoImpl();
		return articleDao.getArticles(userId);
	}
}
