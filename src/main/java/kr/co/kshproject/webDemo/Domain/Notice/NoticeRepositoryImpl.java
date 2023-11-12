package kr.co.kshproject.webDemo.Domain.Notice;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NoticeRepositoryImpl implements NoticeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String,List> findAllWithComments(int page, int pageSize) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> noticeDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<NoticeDTO> cq = cb.createQuery(NoticeDTO.class);

        //from 절
        Root<Notice> notice = cq.from(Notice.class);
        //SELECT id,username,title,contents,email,createdDate FROM Notice LIMIT [pageSize] OFFSET (page-1) * [pageSize] ;
        cq.select(cb.construct(
                NoticeDTO.class,
                notice.get("id"),
                notice.get("username"),
                notice.get("title"),
                notice.get("contents"),
                notice.get("email"),
                notice.get("createdDate")
        ));
        //cq 쿼리 실행
        TypedQuery<NoticeDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=findAllCountNotice(cb);
        tSize=tSize/pageSize+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *pageSize);
        query.setMaxResults(pageSize);

        //맵리스트 저장
        noticeDTOList.put("lists",query.getResultList());
        noticeDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return noticeDTOList;
    }

    @Override
    public Long findAllCountNotice(CriteriaBuilder cb) {
        //게시판 총 카운트
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Notice> countRoot = countQuery.from(Notice.class);
        countQuery.select(cb.count(countRoot));
        Long totalRecords = entityManager.createQuery(countQuery).getSingleResult();
        return totalRecords;
    }


    @Override
    public Optional<Notice> findWithCommentsById(int page,Long id)  {

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Notice 사용 이유는 댓글들을 불러오기때문
        CriteriaQuery<Notice> cq = cb.createQuery(Notice.class);

        /*select * from Notice LEFT JOIN Comments where id='id'*/
        //from Notice
        Root<Notice> notice = cq.from(Notice.class);
        //left join
        notice.fetch("comments", JoinType.LEFT);
        cq.where(cb.equal(notice.get("id"), id));
        //cq 쿼리 실행
        TypedQuery<Notice> query = entityManager.createQuery(cq);
        List<Notice> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));
    }
}
