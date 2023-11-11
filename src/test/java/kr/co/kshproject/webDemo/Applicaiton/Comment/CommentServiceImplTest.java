package kr.co.kshproject.webDemo.Applicaiton.Comment;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeServiceImpl;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Comment.CommentRepository;
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

class CommentServiceImplTest {
    @Mock
    private NoticeRepository noticeRepository;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private NoticeServiceImpl noticeService;
    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEach(){
        noticeService.deleteAll();
        commentService.deleteAll();
    }

    @Test
    void save() throws Exception{
        //생성
        // Notice 생성 및 저장
        Notice notice = new Notice(null, "test", "title", "contents", "email", "date", null);

        // 저장 동작을 모의화
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);

        // Comment 생성 및 저장
        Comment comment = new Comment(null, null, "test", "comment", "date");
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        comment.setNotice(notice);

        Optional<Comment> findComment= Optional.ofNullable(commentService.save(comment));
        assertThat(findComment.get()).isEqualTo(comment);
    }

    @Test
    void findAll() throws Exception{
        //생성
        // Notice 생성 및 저장
        Notice notice= new Notice(null,"test1","title","cotenst","email","date",null);
        // 저장 동작을 모의화
        when(noticeRepository.save(any(Notice.class))).thenReturn(notice);

        // Comment 생성 및 저장
        Comment comment1 = new Comment(null, notice, "test1", "comment1", "date");
        Comment comment2 = new Comment(null, notice, "test2", "comment2", "date");

        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment1, comment2));

        //find
        List<Comment> result = commentService.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(comment1, comment2);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        // 저장 동작을 모의화
        when(noticeRepository.findById(id)).thenReturn(Optional.of(notice));

        Comment comment = new Comment(null, notice, "test", "comment", "date");

        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        Optional<Comment> foundComment = commentService.findById(id);
        assertThat(foundComment.get()).isEqualTo(comment);
    }

    @Test
    void update() throws Exception {
        //생성
        // Notice 생성 및 저장
        Long id = 1L;
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        // 저장 동작을 모의화
        when(noticeRepository.findById(id)).thenReturn(Optional.of(notice));

        Comment existingComment = new Comment(id, notice, "test", "comment", "date");
        Comment updatedComment = new Comment(id, notice, "test", "newContent", "date");
        //when
        when(commentRepository.findById(id)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Comment result = commentService.update(id, updatedComment);

        //then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getContents()).isEqualTo("newContent");
    }
}