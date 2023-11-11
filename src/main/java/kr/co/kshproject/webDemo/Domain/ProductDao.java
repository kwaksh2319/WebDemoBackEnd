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
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void save(Product product) {
        EntityManager entityManager =null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    public List<Product> findAll(int page, int size) {
        List<Product> productList = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((page - 1) * size);
            typedQuery.setMaxResults(size);
            //TODO
            //criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1)); 조건 추가시
            //페이지 조건
             productList = typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return productList;
    }
    public Product findDetail(Long id) {
        Product product = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id)); // 조건 추가
            TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Product> productList = typedQuery.getResultList();
            if (!productList.isEmpty()) {
                product = productList.get(0);
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
        return product;
    }

    public void update(Long id, Product newProduct) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                product.setImageUrl(newProduct.getImageUrl());
                product.setVideoUrl(newProduct.getVideoUrl());
                product.setProductName(newProduct.getProductName());
                product.setDescription(newProduct.getDescription());
                product.setPrice(newProduct.getPrice());

                // 업데이트할 다른 필드들도 변경 가능
                entityManager.merge(product);
                entityManager.getTransaction().commit();
                entityManager.refresh(product);
            } else {
                // 상품이 존재하지 않는 경우 예외 처리
                throw new IllegalArgumentException("Invalid product id: " + id);
            }
            }catch (Exception e){
                e.printStackTrace();
                entityManager.getTransaction().rollback();
            }finally {
                entityManager.close();
            }
    }

    public void delete(Long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            Product product =  entityManager.find(Product.class,id);
            entityManager.getTransaction().begin();

            if(product!=null){
                entityManager.remove(product);
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
}
