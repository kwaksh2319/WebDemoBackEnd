package kr.co.kshproject.webDemo.Applicaiton.Notice;


import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository){
        this.noticeRepository=noticeRepository;
    }

    @Override
    public Notice save(Notice notice) {
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
    public void deleteAll() {
        noticeRepository.deleteAll();
    }

}
