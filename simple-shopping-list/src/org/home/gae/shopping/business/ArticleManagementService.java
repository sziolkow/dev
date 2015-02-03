package org.home.gae.shopping.business;

import org.home.gae.shopping.persistence.Dao;

public class ArticleManagementService {
	
	public void addArticle(String userId, String articleName, String amount) {
    	Dao.INSTANCE.add(userId, 
    			         articleName, 
    			         new Integer(amount));
	}

	public void deleteArticle(String id) {
		Dao.INSTANCE.remove(Long.parseLong(id));	
	}
	
	public void loadArticlesForUser(String userId) {
		
	}
}
