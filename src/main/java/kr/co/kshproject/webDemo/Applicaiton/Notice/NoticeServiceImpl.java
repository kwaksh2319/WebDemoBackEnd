package kr.co.kshproject.webDemo.Applicaiton.Notice;


import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository){
        this.noticeRepository=noticeRepository;
    }

    @Override
    public Notice save(Notice notice) {
        logger.info(notice.getEmail());
        logger.info(notice.getTitle());

        logger.info(notice.getContents());
        LocalDate now = LocalDate.now();
        notice.setCreatedDate(now.toString());
        notice.setUsername("admin");
        logger.info(notice.getCreatedDate());
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
    public void deleteAll() {
        noticeRepository.deleteAll();
    }

}
