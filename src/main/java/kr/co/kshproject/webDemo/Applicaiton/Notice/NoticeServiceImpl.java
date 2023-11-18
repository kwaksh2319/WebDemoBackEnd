package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeCustomRepository;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    private final NoticeRepository noticeRepository;

    private final NoticeCustomRepository noticeCustomRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository,NoticeCustomRepository noticeCustomRepository){
        this.noticeRepository=noticeRepository;
        this.noticeCustomRepository=noticeCustomRepository;
    }

    @Override
    public Notice save(Notice notice) {
        LocalDate now = LocalDate.now();
        notice.setCreatedDate(now.toString());
        notice.setUsername("admin");
        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }

    @Override
    public Map<String,List> findAllWithComments(int page) {
        //admin get pagesize;
        int pageSize=10;
        return noticeCustomRepository.findAllWithComments(page,pageSize);
    }

    @Override
    public Optional<Notice> findWithCommentsById(int page,Long id) {
        Optional<Notice> result= noticeCustomRepository.findWithCommentsById(page,id);
        return result;
    }

    @Override
    public Notice update(Long id,Notice saveNotice) {
        Optional<Notice> findNotice = noticeRepository.findById(id);

        if(findNotice.isPresent()==false){
            return null;
        }

        Notice notice=findNotice.get();
        notice.setTitle(saveNotice.getTitle());
        notice.setContents(saveNotice.getContents());
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteById(Long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        noticeRepository.deleteAll();
    }
}
