package kr.co.kshproject.webDemo.Domain.Notice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NoticeRepositorylTest {
    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        noticeRepository.deleteAll();
    }

    @Test
    public void save() {
        Notice notice=new Notice();
        notice.setUsername("testUserName");
        notice.setTitle("test");
        notice.setContents("testcontent");
        notice.setUsers(null);
        notice.setEmail("test@mail.com");
        notice.setCreatedDate("20231125");

        Notice savedNotice = noticeRepository.save(notice);
        Optional<Notice> foundNotice = noticeRepository.findById(savedNotice.getId());

        assertTrue(foundNotice.isPresent());
        assertEquals("test", foundNotice.get().getTitle());
    }

    @Test
    public void findAll() {
        Notice notice=new Notice();
        notice.setUsername("testUserName");
        notice.setTitle("test");
        notice.setContents("testcontent");
        notice.setUsers(null);
        notice.setEmail("test@mail.com");
        notice.setCreatedDate("20231125");

        Notice savedNotice = noticeRepository.save(notice);

        List<Notice> notices  = noticeRepository.findAll();
        assertFalse(notices.isEmpty());
    }

    @Test
    public void findById() {
        Notice notice=new Notice();
        notice.setUsername("testUserName");
        notice.setTitle("test");
        notice.setContents("testcontent");
        notice.setUsers(null);
        notice.setEmail("test@mail.com");
        notice.setCreatedDate("20231125");

        Notice savedNotice = noticeRepository.save(notice);

        Optional<Notice> foundNotice = noticeRepository.findById(savedNotice.getId());
        assertTrue(foundNotice.isPresent());
        assertEquals("test", foundNotice.get().getTitle());
    }

    @Test
    public void update() {
        Notice notice=new Notice();
        notice.setUsername("testUserName");
        notice.setTitle("test");
        notice.setContents("testcontent");
        notice.setUsers(null);
        notice.setEmail("test@mail.com");
        notice.setCreatedDate("20231125");

        Notice savedNotice = noticeRepository.save(notice);

        Notice noticeToUpdate = noticeRepository.findById(savedNotice.getId()).get();
        noticeToUpdate.setTitle("update test");
        noticeRepository.save(noticeToUpdate);

        Optional<Notice> updatedNotice = noticeRepository.findById(savedNotice.getId());
        assertTrue(updatedNotice.isPresent());
        assertEquals("update test", updatedNotice.get().getTitle());
    }

    @Test
    public void deleteAll() {
        Notice notice=new Notice();
        notice.setUsername("testUserName");
        notice.setTitle("test");
        notice.setContents("testcontent");
        notice.setUsers(null);
        notice.setEmail("test@mail.com");
        notice.setCreatedDate("20231125");

        Notice savedNotice = noticeRepository.save(notice);
        noticeRepository.deleteAll();
        List<Notice> notices = noticeRepository.findAll();
        assertTrue(notices.isEmpty());
    }
}