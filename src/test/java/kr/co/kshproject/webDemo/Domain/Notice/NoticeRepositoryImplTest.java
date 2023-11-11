package kr.co.kshproject.webDemo.Domain.Notice;

import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //세이브
        Notice savedNotice=noticeRepository.save(notice);

        //체크
        Optional<Notice> findNotice=noticeRepository.findById(notice.getId());
        assertThat(findNotice.isPresent()).isTrue();
        assertThat(findNotice.get().getUsername()).isEqualTo(savedNotice.getUsername());
        assertThat(findNotice.get().getId()).isEqualTo(savedNotice.getId());
    }

    @Test
    void findAll() {
        //생성
        Notice notice1= new Notice(null,"test1","title","cotenst","email","date",null);
        Notice notice2= new Notice(null,"test2","title","cotenst","email","date",null);

        //세이브
        Notice savedNotice1 =noticeRepository.save(notice1);
        Notice savedNotice2 =noticeRepository.save(notice2);

        //find
        List<Notice> result = noticeRepository.findAll();

        //check
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.stream().map(Notice::getId)).contains(savedNotice1.getId(), savedNotice2.getId());
    }

    @Test
    void findWithCommentsById(){
        long id=1L;
        List<Comment> comments = new ArrayList<>();
       // comments.add(new Comment(,))
        Notice notice= new Notice(null,"test1","title","cotenst","email","date",comments);
        //세이브
        Notice savedNotice=noticeRepository.save(notice);
        //체크
        Optional<Notice> findNotice=noticeRepository.findWithCommentsById(savedNotice.getId());
        assertThat(findNotice.isPresent()).isTrue();
        assertThat(findNotice.get().getUsername()).isEqualTo(savedNotice.getUsername());
        assertThat(findNotice.get().getId()).isEqualTo(savedNotice.getId());
    }

    @Test
    void update(){
        Notice notice= new Notice(null,"test1","title1","cotenst2","email","date",null);

        Notice savedNotice=noticeRepository.save(notice);

        Optional<Notice> findNotice=noticeRepository.findById(savedNotice.getId());
        findNotice.get().setTitle("title1");
        findNotice.get().setContents("cotenst2");

        Notice updateNotice=noticeRepository.save(findNotice.get());
        assertThat(updateNotice.getId()).isEqualTo(findNotice.get().getId());
        assertThat(updateNotice.getTitle()).isEqualTo(findNotice.get().getTitle());
    }

}