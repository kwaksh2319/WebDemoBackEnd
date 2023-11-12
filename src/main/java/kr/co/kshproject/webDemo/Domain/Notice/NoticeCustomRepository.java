package kr.co.kshproject.webDemo.Domain.Notice;

import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface NoticeCustomRepository {
    public Map<String, List> findAllWithComments(int page, int pageSize);

     Long findAllCountNotice(CriteriaBuilder cb);
    Optional<Notice> findWithCommentsById(int page,Long id) ;
}
