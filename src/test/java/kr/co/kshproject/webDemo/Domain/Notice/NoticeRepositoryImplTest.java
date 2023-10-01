package kr.co.kshproject.webDemo.Domain.Notice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoticeRepositoryImplTest {

    @Autowired
    NoticeRepository noticeRepository;

    @AfterEach
    void afterEach(){
        noticeRepository.deleteAll();
    }

    @Test
    void save() {
        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date");

        //세이브
        Notice savedNotice=noticeRepository.save(notice);

        //체크
        Optional<Notice> findNotice=noticeRepository.findById(notice.getId());
        assertThat(findNotice.isPresent()).isTrue();
        assertThat(findNotice.get()).isEqualTo(savedNotice);
    }

    @Test
    void findAll() {
        //생성
        Notice notice1= new Notice(null,"test1","title","cotenst","email","date");
        Notice notice2= new Notice(null,"test2","title","cotenst","email","date");

        //세이브
        noticeRepository.save(notice1);
        noticeRepository.save(notice2);

        //find
        List<Notice> result = noticeRepository.findAll();

        //check
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(notice1, notice2);
    }
}