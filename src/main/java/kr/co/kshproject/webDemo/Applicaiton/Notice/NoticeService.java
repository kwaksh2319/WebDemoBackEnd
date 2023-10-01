package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeService {
    Notice save(Notice notice);
    List<Notice> findAll();
    Optional<Notice> findById(Long id);
    void deleteAll();
}
