package kr.co.kshproject.webDemo.Domain.Comment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        commentRepository.deleteAll();
    }

    @Test
    public void save() {
        Comment comment=new Comment();
        comment.setUserName("testUserName");
        comment.setContents("test");
        comment.setCreatedDate("20231125");
        comment.setNotice(null);
        comment.setUsers(null);

        Comment savedComment = commentRepository.save(comment);
        Optional<Comment> foundComment = commentRepository.findById(savedComment.getId());

        assertTrue(foundComment.isPresent());
        assertEquals("test", foundComment.get().getContents());
    }

    @Test
    public void findAll() {
        Comment comment=new Comment();
        comment.setUserName("testUserName");
        comment.setContents("test");
        comment.setCreatedDate("20231125");
        comment.setNotice(null);
        comment.setUsers(null);

        Comment savedComment = commentRepository.save(comment);

        List<Comment> comments  = commentRepository.findAll();
        assertFalse(comments.isEmpty());
    }

    @Test
    public void findById() {
        Comment comment=new Comment();
        comment.setUserName("testUserName");
        comment.setContents("test");
        comment.setCreatedDate("20231125");
        comment.setNotice(null);
        comment.setUsers(null);

        Comment savedComment = commentRepository.save(comment);

        Optional<Comment> foundComment = commentRepository.findById(savedComment.getId());
        assertTrue(foundComment.isPresent());
        assertEquals("test", foundComment.get().getContents());
    }

    @Test
    public void update() {
        Comment comment=new Comment();
        comment.setUserName("testUserName");
        comment.setContents("test");
        comment.setCreatedDate("20231125");
        comment.setNotice(null);
        comment.setUsers(null);

        Comment savedComment = commentRepository.save(comment);

        Comment commentToUpdate = commentRepository.findById(savedComment.getId()).get();
        commentToUpdate.setContents("update test");
        commentRepository.save(commentToUpdate);

        Optional<Comment> updatedComment = commentRepository.findById(savedComment.getId());
        assertTrue(updatedComment.isPresent());
        assertEquals("update test", updatedComment.get().getContents());
    }

    @Test
    public void deleteAll() {
        Comment comment=new Comment();
        comment.setUserName("testUserName");
        comment.setContents("test");
        comment.setCreatedDate("20231125");
        comment.setNotice(null);
        comment.setUsers(null);

        Comment savedComment = commentRepository.save(comment);
        commentRepository.deleteAll();
        List<Comment> comments = commentRepository.findAll();
        assertTrue(comments.isEmpty());
    }
}