package kr.co.kshproject.webDemo.Domain.BeginOrder;

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
public class BeginOrderCustomRepositoryImpl implements BeginOrderCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final CommonService commonService;

    @Autowired
    public BeginOrderCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<BeginOrder> findById(Long id) {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<BeginOrder> cq = cb.createQuery(BeginOrder.class);
        //cq 쿼리 실행
        Root<BeginOrder> beginOrder= cq.from(BeginOrder.class);
        cq.where(cb.equal(beginOrder.get("id"), id));

        TypedQuery<BeginOrder> query = entityManager.createQuery(cq);

        List<BeginOrder> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<BeginOrderDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeginOrderDTO> cq = cb.createQuery(BeginOrderDTO.class);
        Root<BeginOrder> beginOrder = cq.from(BeginOrder.class);
        cq.select(cb.construct(
                BeginOrderDTO.class,
                beginOrder.get("id"),
                beginOrder.get("quantity"),
                beginOrder.get("picture"),
                beginOrder.get("createdDate"),
                beginOrder.get("updateDate"),
                beginOrder.get("products").get("id"),
                beginOrder.get("orders").get("id")

        ));
        TypedQuery<BeginOrderDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> beginOrderDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<BeginOrderDTO> cq = cb.createQuery(BeginOrderDTO.class);

        //from 절
        Root<BeginOrder> beginOrder = cq.from(BeginOrder.class);

        cq.select(cb.construct(
                BeginOrderDTO.class,
                beginOrder.get("id"),
                beginOrder.get("quantity"),
                beginOrder.get("picture"),
                beginOrder.get("createdDate"),
                beginOrder.get("updateDate"),
                beginOrder.get("products").get("id"),
                beginOrder.get("orders").get("id")

        ));
        //cq 쿼리 실행
        TypedQuery<BeginOrderDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb,BeginOrder.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);

        //맵리스트 저장
        beginOrderDTOList.put("lists",query.getResultList());
        beginOrderDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return beginOrderDTOList;
    }
}
