package kr.co.kshproject.webDemo.Domain.Products;

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
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    private final CommonService commonService;

    @Autowired
    public ProductsCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<Products> findById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Notice 사용 이유는 댓글들을 불러오기때문
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> product = cq.from(Products.class);
        product.fetch("baskets", JoinType.LEFT);
        product.fetch("beginOrders", JoinType.LEFT);

        cq.where(cb.equal(product.get("id"), id));
        //cq 쿼리 실행
        TypedQuery<Products> query = entityManager.createQuery(cq);
        List<Products> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<ProductsDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductsDTO> cq = cb.createQuery(ProductsDTO.class);
        Root<Products> product = cq.from(Products.class);
        cq.select(cb.construct(
                ProductsDTO.class,
                product.get("id"),
                product.get("productName"),
                product.get("describe"),
                product.get("price"),
                product.get("picture"),
                product.get("soldOut"),
                product.get("createdDate"),
                product.get("updateDate"),
                product.get("category").get("id")
        ));
        TypedQuery<ProductsDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> productDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<ProductsDTO> cq = cb.createQuery(ProductsDTO.class);

        //from 절
        Root<Products> product = cq.from(Products.class);

        cq.select(cb.construct(
                ProductsDTO.class,
                product.get("id"),
                product.get("productName"),
                product.get("describe"),
                product.get("price"),
                product.get("picture"),
                product.get("soldOut"),
                product.get("createdDate"),
                product.get("updateDate"),
                product.get("category").get("id")
        ));
        //cq 쿼리 실행
        TypedQuery<ProductsDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb,Products.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);

        //맵리스트 저장
        productDTOList.put("lists",query.getResultList());
        productDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return productDTOList;
    }
}
