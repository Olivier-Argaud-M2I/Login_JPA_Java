package fr.m2i.login.crud;

import fr.m2i.login.models.News;
import fr.m2i.login.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import java.util.List;

public class CrudUser {

    private EntityManagerFactory factory;

    public CrudUser(){
        this.factory = Persistence.createEntityManagerFactory("databaseM2I2");
    }


    public List<User> selectAll(){
        EntityManager em = factory.createEntityManager();
        List<User> users = em.createNamedQuery("selectAllUser").getResultList();
        em.close();
        return users;
    }

    public User addUser(User user){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        boolean valid = false;
        try{
//            em.persist(user);
            em.createNamedQuery("addUser")
                    .setParameter(1,user.getUsername())
                    .setParameter(2,user.getPassword())
                    .executeUpdate();
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
//        em.refresh(user);
        em.close();
        return user;
    }

    public User findById(Integer id){
        EntityManager em = factory.createEntityManager();
        User user = em.find(User.class,id);
        em.close();
        return user;
    }

    public User findByName(String username){
        EntityManager em = factory.createEntityManager();
        User user = null;
        try{
             user =(User)em.createNamedQuery("findUserByUsername")
                    .setParameter("username",username)
                    .getResultList().get(0);
        }catch (Exception e){
        }

        em.close();
        return user;
    }



    public void deleteById(Integer id){
        EntityManager em = factory.createEntityManager();
        User user = em.find(User.class,id);
        em.getTransaction().begin();
        boolean valid = false;
        try{
//            em.createNamedQuery("deleteUserById")
//                    .setParameter("id",id)
//                    .executeUpdate();

            em.remove(user);
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
    
    public User update(Integer id,String username,String password){
        EntityManager em = factory.createEntityManager();
        User user1 = em.find(User.class,id);

        em.getTransaction().begin();
        boolean valid = false;
        try{
            user1.setUsername(username);
            user1.setPassword(password);
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
        return user1;
    }


}