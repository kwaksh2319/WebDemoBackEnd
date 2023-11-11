package kr.co.kshproject.webDemo.Domain.Notice;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Slf4j
public class NoticeRepositoryImpl implements NoticeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NoticeDTO> findAllWithComments() {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<NoticeDTO> cq = cb.createQuery(NoticeDTO.class);

        //from 절
        Root<Notice> notice = cq.from(Notice.class);
        //선택 및 조건 설정 =>select id,username,title,contents,email,createdDate from notice;
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
        //결과값 리턴
        return query.getResultList();
    }


    @Override
    public Optional<Notice> findWithCommentsById(Long id)  {
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
