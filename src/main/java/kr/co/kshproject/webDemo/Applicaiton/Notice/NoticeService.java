package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NoticeService {
    Notice save(NoticeDTO noticeDTO);
    List<NoticeDTO> findAll();
    Map<String,List> findAll(int page, int size);
    Optional<Notice> findById(Long id);
    Notice update(Long id,NoticeDTO noticeDTO);
    void deleteById(Long id);
    void deleteAll();
}
