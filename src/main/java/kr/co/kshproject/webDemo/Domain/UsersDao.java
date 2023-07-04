package kr.co.kshproject.webDemo.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void save(Users user) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Users findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Users.class, id);
    }
    public Users findByUsername(String username) {
        Users users = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
            Root<Users> root = criteriaQuery.from(Users.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username)); // 조건 추가
            TypedQuery<Users> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Users> usersList = typedQuery.getResultList();
            if (!usersList.isEmpty()) {
                users = usersList.get(0);
            } else {
                throw new NoResultException("No entity found for id " + username);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {  //ssaaa
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return users;
    }
}
