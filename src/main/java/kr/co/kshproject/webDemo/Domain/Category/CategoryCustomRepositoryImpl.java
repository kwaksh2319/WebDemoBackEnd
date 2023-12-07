package kr.co.kshproject.webDemo.Domain.Category;

import kr.co.kshproject.webDemo.Common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final CommonService commonService;

    @Autowired
    public CategoryCustomRepositoryImpl( CommonService commonService ){
        this.commonService=commonService;
    }

    @Override
    public Optional<Category> findById(Long id) {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Category> cq = cb.createQuery(Category.class);

        Root<Category> category= cq.from(Category.class);
        category.fetch("products", JoinType.LEFT);
        category.fetch("childCategories", JoinType.LEFT);
        cq.where(cb.equal(category.get("id"), id));
        //cq 쿼리 실행
        TypedQuery<Category> query = entityManager.createQuery(cq);
        List<Category> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<CategoryDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryDTO> cq = cb.createQuery(CategoryDTO.class);
        Root<Category> category = cq.from(Category.class);
        cq.select(cb.construct(
                CategoryDTO.class,
                category.get("id"),
                category.get("categoryName"),
                category.get("createdDate"),
                category.get("updateDate"),
                category.get("parentCategory").get("id")
        ));
        TypedQuery<CategoryDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();

        Map<String,List> categoryDTOList=new ConcurrentHashMap<>();
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<CategoryDTO> cq = cb.createQuery(CategoryDTO.class);

        //from 절
        Root<Category> category = cq.from(Category.class);

        cq.select(cb.construct(
                CategoryDTO.class,
                category.get("id"),
                category.get("categoryName"),
                category.get("createdDate"),
                category.get("updateDate"),
                category.get("parentCategory").get("id")
        ));
        //cq 쿼리 실행
        TypedQuery<CategoryDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb, Category.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);
        //맵리스트 저장
        categoryDTOList.put("lists",query.getResultList());
        categoryDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return categoryDTOList;
    }
}
