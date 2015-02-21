package org.home.gae.shopping.business;

import java.util.ArrayList;
import java.util.List;

import org.home.gae.shopping.model.ArticleDTO;
import org.home.gae.shopping.persistence.Article;
import org.home.gae.shopping.persistence.ArticleDao;
import org.home.gae.shopping.persistence.ArticleDaoImpl;

public class ArticleManagementService {
	
	public ArticleDTO addArticle(String userId, String articleName, String amount) {
		ArticleDao articleDao = new ArticleDaoImpl();
		Article newArticle = articleDao.add(userId, articleName, new Integer(amount));
		return new ArticleDTO(newArticle.getId(), 
				newArticle.getUser(), 
				newArticle.getName(), 
				newArticle.getAmount());
	}

	public void deleteArticle(String id) {
		ArticleDao articleDao = new ArticleDaoImpl();
		articleDao.remove(Long.parseLong(id));	
	}
	
	public List<ArticleDTO> getArticles(String userId) {
		ArticleDao articleDao = new ArticleDaoImpl();
		List<Article> entityList = articleDao.getArticles(userId);
		return convertToDtoList(entityList);
	}
	
	private List<ArticleDTO> convertToDtoList(List<Article> entityList) {
		List<ArticleDTO> dtoList =  new ArrayList<ArticleDTO>();
		for (Article entity : entityList) {
			ArticleDTO newDto = new ArticleDTO(entity.getId(), 
					entity.getUser(), entity.getName(), entity.getAmount());
			dtoList.add(newDto);
		}
		return dtoList;
	}
}
