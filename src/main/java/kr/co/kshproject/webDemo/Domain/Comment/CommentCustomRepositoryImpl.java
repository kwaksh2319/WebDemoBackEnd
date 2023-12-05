package kr.co.kshproject.webDemo.Domain.Comment;

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
public class CommentCustomRepositoryImpl implements CommentCustomRepository{
    @PersistenceContext
    private EntityManager entityManager;

    private final CommonService commonService;

    @Autowired
    public CommentCustomRepositoryImpl( CommonService commonService){
        this.commonService=commonService;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Notice 사용 이유는 댓글들을 불러오기때문
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);

        //from Notice
        Root<Comment> comment = cq.from(Comment.class);

        //cq 쿼리 실행
        TypedQuery<Comment> query = entityManager.createQuery(cq);
        List<Comment> result = query.getResultList();
        //삼항 연산자 result 비엇을시 empty() 존재지 resutl 리턴
        return result.isEmpty() ? Optional.empty(): Optional.of(result.get(0));

    }

    @Override
    public List<CommentDTO> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentDTO> cq = cb.createQuery(CommentDTO.class);
        Root<Comment> comment = cq.from(Comment.class);
        cq.select(cb.construct(
                CommentDTO.class,
                comment.get("id"),
                comment.get("userName"),
                comment.get("contents"),
                comment.get("createdDate"),
                comment.get("notice").get("id"),
                comment.get("users").get("id")

        ));
        TypedQuery<CommentDTO> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Map<String, List> findAll(int page, int size) {
        List<Long> totalSize =new LinkedList<>();
        Map<String,List> commentDTOList=new ConcurrentHashMap<>();

        //Criteria API 사용
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //NoticeDTO <= Notice 커멘트 사용하지 않기 위함
        CriteriaQuery<CommentDTO> cq = cb.createQuery(CommentDTO.class);

        //from 절
        Root<Comment> comment = cq.from(Comment.class);

        cq.select(cb.construct(
                CommentDTO.class,
                comment.get("id"),
                comment.get("userName"),
                comment.get("contents"),
                comment.get("createdDate"),
                comment.get("notice").get("id"),
                comment.get("users").get("id")
        ));
        //cq 쿼리 실행
        TypedQuery<CommentDTO> query = entityManager.createQuery(cq);
        //게시판 총사이즈
        Long tSize=commonService.findAllCount(entityManager,cb,Comment.class);
        tSize=tSize/size+1;
        totalSize.add(tSize);

        //cq 쿼리 실행 후 페이지 갯수만큼 불러오기
        query.setFirstResult( (page-1) *size);
        query.setMaxResults(size);

        //맵리스트 저장
        commentDTOList.put("lists",query.getResultList());
        commentDTOList.put("totalSize",totalSize);

        //결과값 리턴
        return commentDTOList;
    }
}
