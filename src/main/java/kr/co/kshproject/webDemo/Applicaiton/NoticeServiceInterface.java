package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Notice;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NoticeServiceInterface {

    public void save(Notice notice, HttpSession session) ;
    public List<Notice> findAll(int page, int size) ;
    public void deleteNoticeDetail(Long id);
    public Notice findDetail(Long id);
    public void update(Long id, Notice newNotice);
}
