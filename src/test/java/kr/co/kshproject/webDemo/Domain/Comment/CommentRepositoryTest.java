package kr.co.kshproject.webDemo.Domain.Comment;

import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.Domain.Notice.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NoticeRepository noticeRepository;

    @AfterEach
    void afterEach(){
        commentRepository.deleteAll();
    }

    @Test
    @Transactional
    void save() {
        //생성
        // Notice 생성 및 저장
        Notice notice = new Notice(null, "test", "title", "contents", "email", "date", null);
        Notice savedNotice = noticeRepository.save(notice);

        // Comment 생성 및 저장
        Comment comment = new Comment(null, savedNotice, "test", "comment", "date");
        Comment savedComment = commentRepository.save(comment);

        // Comment 조회
        Optional<Comment> foundComment = commentRepository.findById(savedComment.getId());

        // 검증
        assertThat(foundComment.isPresent()).isTrue();
        assertThat(foundComment.get().getNotice()).isNotNull(); // 지연 로딩으로 인해 Notice 객체가 실제로 로드되었는지 확인합니다.
        assertThat(foundComment.get()).isEqualTo(savedComment);
    }

    @Test
    @Transactional
    void findAll() {
        //생성
        Notice notice = new Notice(null, "test", "title", "contents", "email", "date", null);
        Notice savedNotice = noticeRepository.save(notice);

        Comment comment1 = new Comment(null, savedNotice, "test1", "comment", "date");
        Comment comment2 = new Comment(null, savedNotice, "test2", "comment", "date");
        Comment savedComment1 = commentRepository.save(comment1);
        Comment savedComment2 = commentRepository.save(comment2);

        List<Comment> result = commentRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(savedComment1, savedComment2);
    }

    @Test
    @Transactional
    void findById(){
        long id=1L;
        Notice notice= new Notice(null,"test1","title","cotenst","email","date",null);
        //세이브
        Notice savedNotice=noticeRepository.save(notice);

        Comment comment1 = new Comment(null, savedNotice, "test1", "comment", "date");
        Comment comment2 = new Comment(null, savedNotice, "test2", "comment", "date");
        Comment savedComment1 = commentRepository.save(comment1);
        Comment savedComment2 = commentRepository.save(comment2);

        //체크
        Optional<Comment> findNotice1=commentRepository.findById(savedComment1.getId());
        assertThat(findNotice1.isPresent()).isTrue();
        assertThat(findNotice1.get()).isEqualTo(savedComment1);

        //체크
        Optional<Comment> findNotice2=commentRepository.findById(savedComment2.getId());
        assertThat(findNotice2.isPresent()).isTrue();
        assertThat(findNotice2.get()).isEqualTo(savedComment2);
    }

    @Test
    @Transactional
    void update(){
        Notice notice= new Notice(null,"test1","title1","cotenst2","email","date",null);

        Notice savedNotice=noticeRepository.save(notice);

        // Comment 생성 및 저장
        Comment comment = new Comment(null, savedNotice, "test", "comment", "date");
        Comment savedComment = commentRepository.save(comment);

        // Comment 조회
        Optional<Comment> foundComment = commentRepository.findById(savedComment.getId());

        foundComment.get().setContents("cotenst2");

        Comment updateComment=commentRepository.save(foundComment.get());
        assertThat(updateComment.getId()).isEqualTo(foundComment.get().getId());
        assertThat(updateComment.getContents()).isEqualTo(foundComment.get().getContents());
    }
}