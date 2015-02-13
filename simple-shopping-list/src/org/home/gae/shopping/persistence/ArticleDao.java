package org.home.gae.shopping.persistence;

import java.util.List;

import org.home.gae.shopping.model.Article;

public interface ArticleDao {
	
	List<Article> listArticles();

    void add(String user, String name, int amount);

    List<Article> getArticles(String userId);
    
    void remove(long id);
}
