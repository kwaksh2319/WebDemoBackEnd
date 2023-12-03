package kr.co.kshproject.webDemo.Domain.Users;

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
public class UsersCustomRepositoryImpl implements UsersCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    private final CommonService commonService;

    @Autowired
    public UsersCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<Users> findById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Notice 사용 이유는 댓글들을 불러오기때문
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> user = cq.from(Users.class);
        user.fetch("comments", JoinType.LEFT);
        user.fetch("notices", JoinType.LEFT);
        user.fetch("baskets", JoinType.LEFT);
        user.fetch("orders", JoinType.LEFT);

        cq.where(cb.equal(user.get("id"), id));
        //cq 쿼리 실행
        TypedQuery<Users> query = entityManager.createQuery(cq);
        List<Users> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public List<UsersDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDTO> cq = cb.createQuery(UsersDTO.class);
        Root<Users> user = cq.from(Users.class);
        cq.select(cb.construct(
                UsersDTO.class,
                user.get("id"),
                user.get("username"),
                user.get("email"),
                user.get("name"),
                user.get("level"),
                user.get("password")
        ));
        TypedQuery<UsersDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> userDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<UsersDTO> cq = cb.createQuery(UsersDTO.class);

        //from 절
        Root<Users> user = cq.from(Users.class);

        cq.select(cb.construct(
                UsersDTO.class,
                user.get("id"),
                user.get("username"),
                user.get("email"),
                user.get("name"),
                user.get("level"),
                user.get("password")
        ));
        //cq 쿼리 실행
        TypedQuery<UsersDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb,Users.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);

        //맵리스트 저장
        userDTOList.put("lists",query.getResultList());
        userDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return userDTOList;
    }
}
