package fr.m2i.login.crud;

import fr.m2i.login.models.News;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CrudNews {

    private EntityManagerFactory factory;

    public CrudNews(){
        this.factory = Persistence.createEntityManagerFactory("databaseM2I2");
    }


    public List<News> selectAll(){
        EntityManager em = factory.createEntityManager();
        List<News> news = em.createNamedQuery("selectAllNews").getResultList();
        em.close();
        return news;
    }

    public List<News> selectLastX(Integer nb){
        EntityManager em = factory.createEntityManager();
        List<News> news = em.createNamedQuery("selectLastXNews")
                .setParameter("x",nb)
                .getResultList();
        em.close();
        return news;
    }

    public News addNews(News news){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        boolean valid = false;
        try{
            em.persist(news);
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.refresh(news);
        em.close();
        return news;
    }

    public News findById(Integer id){
        EntityManager em = factory.createEntityManager();
        News news = em.find(News.class,id);
        em.close();
        return news;
    }

    public void deleteById(Integer id){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        boolean valid = false;
        try{
//            em.createNamedQuery("deleteNewsById")
//                    .setParameter("id",id)
//                    .executeUpdate();
            em.remove(em.find(News.class,id));
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.close();
    }

    public News update(News news){
        EntityManager em = factory.createEntityManager();
        News news1 = em.find(News.class,news.getId());
        news1.setTitre(news.getTitre());
        news1.setText(news.getText());
        em.getTransaction().begin();
        boolean valid = false;
        try{
            em.merge(news1);
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.refresh(news1);
        em.close();
        return news1;
    }


}

