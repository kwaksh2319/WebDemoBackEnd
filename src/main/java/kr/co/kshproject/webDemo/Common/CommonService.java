package kr.co.kshproject.webDemo.Common;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class CommonService {
    public <T> Long findAllCount(EntityManager entityManager ,CriteriaBuilder cb,Class<T> entityClass) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<T> root = countQuery.from(entityClass);
        countQuery.select(cb.count(root));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
