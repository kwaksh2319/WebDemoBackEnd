package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeCustomRepository;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeDTO;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    private final NoticeCustomRepository noticeCustomRepository;

    private final UserService userService;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository,
                             NoticeCustomRepository noticeCustomRepository,
                             UserService userService){
        this.noticeRepository=noticeRepository;
        this.noticeCustomRepository=noticeCustomRepository;
        this.userService=userService;
    }

    @Override
    public Notice save(NoticeDTO noticeDTO) {
        Notice notice=new Notice(noticeDTO);
        return noticeRepository.save(notice);
    }

    @Override
    public List<NoticeDTO> findAll() {
        return noticeCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return noticeCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return noticeCustomRepository.findById(id);
    }

    @Override
    public Notice update(Long id,NoticeDTO noticeDTO) {
        return noticeRepository.save(ConverEntity(id,noticeDTO));
    }

    @Override
    public void deleteById(Long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        noticeRepository.deleteAll();
    }

    private Notice ConverEntity(Long id, NoticeDTO noticeDTO){

        Optional<Notice> notice=noticeCustomRepository.findById(id);
        Long userId=noticeDTO.getUserId();
        Optional<Users> updateUsers =userService.findById(userId);
        notice.get().setUsername(noticeDTO.getUsername());
        notice.get().setTitle(noticeDTO.getTitle());
        notice.get().setContents(noticeDTO.getContents());
        notice.get().setEmail(noticeDTO.getEmail());
        notice.get().setCreatedDate(noticeDTO.getCreatedDate());

        if(updateUsers.isPresent()==true){
            notice.get().setUsers( updateUsers.get());
        }

        return notice.get();
    }
}
