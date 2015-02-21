package org.home.gae.shopping.persistence;

import java.util.List;

public interface ArticleDao {
	
	List<Article> listArticles();

    Article add(String user, String name, int amount);

    List<Article> getArticles(String userId);
    
    void remove(long id);
}
