package kr.co.kshproject.webDemo.Domain.Notice;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface NoticeCustomRepository {
    Optional<Notice> findById(Long id);
    List<NoticeDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
