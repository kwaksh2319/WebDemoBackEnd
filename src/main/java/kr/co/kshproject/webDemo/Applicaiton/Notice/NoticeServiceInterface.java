package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
@Service
public interface NoticeServiceInterface {

    <S extends Notice> S save(S entity, HttpSession session);
    Optional<Notice> findById(Long id);
    boolean existsById(Long id);
    List<Notice> findAll();
    long count();
    void deleteById(Long id);
    void delete(Notice entity);
    void deleteAll();

    /*
    public void save(Notice notice, HttpSession session) ;
    public List<Notice> findAll(int page, int size) ;
    public void deleteNoticeDetail(Long id);
    public Notice findDetail(Long id);
    public void update(Long id, Notice newNotice);*/
}
