package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeCustomRepository;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeDTO;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    public List<NoticeDTO> findAllWithComments() {
        return noticeCustomRepository.findAllWithComments();
    }

    @Override
    public Optional<Notice> findWithCommentsById(Long id) {
            Optional<Notice> result= noticeCustomRepository.findWithCommentsById(id);
            logger.info(result.get().getTitle());
           return result;
    }
    /*
@Transactional(readOnly = true)
@Override
public NoticeDTO findWithCommentsById(Long id) {
  return noticeRepository.findWithCommentsById(id).map(notice -> {
      // Notice 엔티티를 NoticeDTO로 변환
      NoticeDTO noticeDTO = new NoticeDTO();
      // 기타 필드들을 채워넣는 로직...

      // comments 필드를 채워넣습니다.
      noticeDTO.setComments(notice.getComments().stream()
              .map(comment -> new CommentDTO(comment))
              .collect(Collectors.toList()));
      return noticeDTO;
  }).orElseThrow(() -> new EntityNotFoundException("Notice not found with id: " + id));
}
*/
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
