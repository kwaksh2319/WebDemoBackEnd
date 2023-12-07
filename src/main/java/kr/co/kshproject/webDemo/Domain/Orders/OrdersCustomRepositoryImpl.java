package kr.co.kshproject.webDemo.Domain.Orders;

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
public class OrdersCustomRepositoryImpl implements OrdersCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final CommonService commonService;

    @Autowired
    public OrdersCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Notice 사용 이유는 댓글들을 불러오기때문
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);

        Root<Orders> order = cq.from(Orders.class);
        //left join
        order.fetch("beginOrders", JoinType.LEFT);
        cq.where(cb.equal(order.get("id"), id));
        //cq 쿼리 실행
        TypedQuery<Orders> query = entityManager.createQuery(cq);
        List<Orders> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<OrdersDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrdersDTO> cq = cb.createQuery(OrdersDTO.class);
        Root<Orders> order = cq.from(Orders.class);

        cq.select(cb.construct(
                OrdersDTO.class,
                order.get("id"),
                order.get("payment"),
                order.get("status"),
                order.get("price"),
                order.get("cancel"),
                order.get("createdDate"),
                order.get("updateDate"),
                order.get("users").get("id")
        ));

        TypedQuery<OrdersDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();

        Map<String,List> orderDTOList=new ConcurrentHashMap<>();
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<OrdersDTO> cq = cb.createQuery(OrdersDTO.class);

        //from 절
        Root<Orders> order = cq.from(Orders.class);

        cq.select(cb.construct(
                OrdersDTO.class,
                order.get("id"),
                order.get("payment"),
                order.get("status"),
                order.get("price"),
                order.get("cancel"),
                order.get("createdDate"),
                order.get("updateDate"),
                order.get("users").get("id")
        ));
        //cq 쿼리 실행
        TypedQuery<OrdersDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb, Orders.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);
        //맵리스트 저장
        orderDTOList.put("lists",query.getResultList());
        orderDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return orderDTOList;
    }
}
