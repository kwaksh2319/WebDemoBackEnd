package kr.co.kshproject.webDemo.Domain.Notice;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeCustomRepository {
    List<NoticeDTO> findAllWithComments();
    Optional<Notice> findWithCommentsById(Long id) ;
}
