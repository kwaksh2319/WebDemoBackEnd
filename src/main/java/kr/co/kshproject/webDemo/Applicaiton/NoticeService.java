package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Notice;
import kr.co.kshproject.webDemo.Domain.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService implements NoticeServiceInterface{
    @Autowired
    private NoticeDao noticeDao;

    public void save(Notice notice) {
        noticeDao.save(notice);
    }
    public List<Notice> findAll(int page, int size) {

        return noticeDao.findAll(page,size);
    }
    public void deleteNoticeDetail(Long id){
        noticeDao.delete(id);
    }

    public Notice findDetail(Long id) {
        return noticeDao.findDetail(id);
    }
    public void update(Long id, Notice newNotice) {
        noticeDao.update(id,newNotice);
    }
}
