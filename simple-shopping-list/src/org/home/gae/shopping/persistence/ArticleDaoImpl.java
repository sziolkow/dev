package org.home.gae.shopping.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ArticleDaoImpl implements ArticleDao{
 
  @Override
  public List<Article> listArticles() {
    EntityManager em = EMFService.get().createEntityManager();
    // read the existing entries
    Query q = em.createQuery("select m from Article m");
    List<Article> articles = q.getResultList();
    return articles;
  }

  @Override
  public Article add(String user, String name, int amount) {
	EntityManager em = EMFService.get().createEntityManager();
	try {  
      em.getTransaction().begin();		
      Article article = new Article(user, name, amount, "NEW");
      em.persist(article);
      em.flush();      
      em.getTransaction().commit();
      return article;
    } finally {
      em.close();
    }
  }

  @Override
  public List<Article> getArticles(String userId) {
    EntityManager em = EMFService.get().createEntityManager();
    em.getTransaction().begin();	
    Query q = em
        .createQuery("select t from Article t where t.user = :userId");
    q.setParameter("userId", userId);
    List<Article> articles = q.getResultList();
    em.getTransaction().commit();
    em.close();
    return articles;
  }

  @Override
  public void remove(long id) {
    EntityManager em = EMFService.get().createEntityManager();
    em.getTransaction().begin();
    try {
      Article article = em.find(Article.class, id);
      em.remove(article);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
}
