package kr.co.kshproject.webDemo.Domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BasketsDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //장바구니 리스트
    public List<BasketsWithProduct> findAll(String userName,String status, int page, int size) {
        List<BasketsWithProduct> basketsList = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<BasketsWithProduct> criteriaQuery = criteriaBuilder.createQuery(BasketsWithProduct.class);

            Root<Baskets> root = criteriaQuery.from(Baskets.class);

            Join<Baskets, Product> fileJoin = root.join("product", JoinType.INNER);

            // 복합키 검색 조건 생성
            Predicate compositeKeyPredicate = criteriaBuilder.equal(root.get("basketId").get("usersId"),userName );

            // 상태 검색 조건 생성
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);

            // 검색 조건 결합
            Predicate finalPredicate = criteriaBuilder.and(compositeKeyPredicate, statusPredicate);

            //검색
            criteriaQuery.select(criteriaBuilder.construct( BasketsWithProduct.class, root, fileJoin ));
            criteriaQuery.where(finalPredicate);

            TypedQuery<BasketsWithProduct> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((page - 1) * size);
            typedQuery.setMaxResults(size);

            // 페이지 조건
            basketsList = typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return basketsList;
    }

    public List<Baskets> findAll(String userName) {
        List<Baskets> basketsList = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Baskets> criteriaQuery = criteriaBuilder.createQuery(Baskets.class);

            Root<Baskets> root = criteriaQuery.from(Baskets.class);

            // 복합키 검색 조건 생성
            Predicate compositeKeyPredicate = criteriaBuilder.equal(root.get("basketId").get("usersId"), userName);

            // 상태 검색 조건 생성
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), "B");

            // 검색 조건 결합
            Predicate finalPredicate = criteriaBuilder.and(compositeKeyPredicate, statusPredicate);

            criteriaQuery.select(root);
            criteriaQuery.where(finalPredicate);

            TypedQuery<Baskets> typedQuery = entityManager.createQuery(criteriaQuery);
            // typedQuery.setFirstResult((page - 1) * size);
            // typedQuery.setMaxResults(size);
            //    criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1)); 조건 추가시
            // 페이지 조건
            basketsList = typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return basketsList;
    }

    //장바구니 등록
    public void save(Baskets baskets) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(baskets);
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

    //장바구니 삭제 및 결제
    //update로 처리
    public void update(String bindNumber,String status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Baskets baskets = entityManager.find(Baskets.class, bindNumber);
            if (baskets != null) {
               // baskets.setStatus(status);

                // 업데이트할 다른 필드들도 변경 가능
                entityManager.merge(baskets);
                entityManager.getTransaction().commit();
                entityManager.refresh(baskets);
            } else {
                // 상품이 존재하지 않는 경우 예외 처리
                throw new IllegalArgumentException("Invalid product id: " + bindNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

}
