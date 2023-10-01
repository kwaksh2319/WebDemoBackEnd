package kr.co.kshproject.webDemo.Applicaiton.Notice;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NoticeServiceImplTest {

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeServiceImpl noticeService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEach(){
        noticeService.deleteAll();
    }

    @Test
    void save() {
        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date");

        // 저장 동작을 모의화
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);

        //체크
        Optional<Notice> findNotice= Optional.ofNullable(noticeService.save(notice));
        assertThat(findNotice.get()).isEqualTo(notice);
    }

    @Test
    void findAll() {
        //생성
        Notice notice1= new Notice(null,"test1","title","cotenst","email","date");
        Notice notice2= new Notice(null,"test2","title","cotenst","email","date");

        when(noticeRepository.findAll()).thenReturn(Arrays.asList(notice1, notice2));

        //find
        List<Notice> result = noticeRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(notice1, notice2);
    }

    @Test
    void findById() throws Exception {
        //생성
        Long id = 1L;
        Notice notice= new Notice(null,"test","title","cotenst","email","date");

        // 저장 동작을 모의화
        when(noticeRepository.findById(id)).thenReturn(Optional.of(notice));

        // When
        Optional<Notice> foundNotice = noticeService.findById(id);
        assertThat(foundNotice.get()).isEqualTo(notice);
    }
}