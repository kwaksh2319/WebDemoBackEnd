package kr.co.kshproject.webDemo.Domain.Baskets;

import kr.co.kshproject.webDemo.Common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BasketsCustomRepositoryImpl implements BasketsCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final CommonService commonService;

    @Autowired
    public BasketsCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<Baskets> findById(Long id) {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Baskets> cq = cb.createQuery(Baskets.class);
        //cq 쿼리 실행
        Root<Baskets> basket= cq.from(Baskets.class);
        TypedQuery<Baskets> query = entityManager.createQuery(cq);
        cq.where(cb.equal(basket.get("id"), id));
        List<Baskets> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<BasketsDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BasketsDTO> cq = cb.createQuery(BasketsDTO.class);
        Root<Baskets> basket = cq.from(Baskets.class);
        cq.select(cb.construct(
                BasketsDTO.class,
                basket.get("id"),
                basket.get("productName"),
                basket.get("productStatus"),
                basket.get("quantity"),
                basket.get("createDate"),
                basket.get("updateDate"),
                basket.get("products").get("id"),
                basket.get("users").get("id")
        ));
        TypedQuery<BasketsDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> basketDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<BasketsDTO> cq = cb.createQuery(BasketsDTO.class);

        //from 절
        Root<Baskets> basket = cq.from(Baskets.class);

        cq.select(cb.construct(
                BasketsDTO.class,
                basket.get("id"),
                basket.get("productName"),
                basket.get("productStatus"),
                basket.get("quantity"),
                basket.get("createDate"),
                basket.get("updateDate"),
                basket.get("products").get("id"),
                basket.get("users").get("id")
        ));

        //cq 쿼리 실행
        TypedQuery<BasketsDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb,Baskets.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);

        //맵리스트 저장
        basketDTOList.put("lists",query.getResultList());
        basketDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return basketDTOList;
    }
}
