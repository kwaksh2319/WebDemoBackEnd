/*package kr.co.kshproject.webDemo.Domain;*/
/*

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NoticeDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Notice> findAll(int page, int size) {
        List<Notice> noticeList = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Notice> criteriaQuery = criteriaBuilder.createQuery(Notice.class);

            Root<Notice> root = criteriaQuery.from(Notice.class);
            Join<Notice, File> fileJoin = root.join("file", JoinType.LEFT); // Fetch Join

            criteriaQuery.select(root).distinct(true);

            TypedQuery<Notice> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((page - 1) * size);
            typedQuery.setMaxResults(size);
            //TODO
            //criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1)); 조건 추가시
            // 페이지 조건
            noticeList = typedQuery.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return noticeList;
    }

    public void save(Notice notice) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(notice);
            entityManager.getTransaction().commit();

        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }

    public Notice findDetail(Long id) {
        Notice notice = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Notice> criteriaQuery = criteriaBuilder.createQuery(Notice.class);
            Root<Notice> root = criteriaQuery.from(Notice.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id)); // 조건 추가
            TypedQuery<Notice> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Notice> noticeList = typedQuery.getResultList();
            if (!noticeList.isEmpty()) {
                notice = noticeList.get(0);
            } else {
                throw new NoResultException("No entity found for id " + id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {  //ssaaa
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return notice;
    }

    public void update(Long id, Notice newNotice) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            // entityManager에서 notice 데이터 찾기 id로 asdasd
            Notice notice = entityManager.find(Notice.class, id);
            if (notice != null) {
                //notice  업데이트
                notice.setTitle(newNotice.getTitle());
                notice.setEmail(newNotice.getEmail());
                notice.setContents(newNotice.getContents());

                entityManager.merge(notice);
                entityManager.getTransaction().commit();
                entityManager.refresh(notice);

            }else {
                throw new IllegalArgumentException("Invalid product id: " + id);
            }
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
    }

    public void delete(Long id){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
          Notice notice =  entityManager.find(Notice.class,id);
          entityManager.getTransaction().begin();

          if(notice!=null){
              entityManager.remove(notice);
              entityManager.getTransaction().commit();
          }
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }
}*/
