package kr.co.kshproject.webDemo.interfaces.Comment;

import kr.co.kshproject.webDemo.Applicaiton.Comment.CommentService;
import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Comment.Comment;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import kr.co.kshproject.webDemo.interfaces.Notice.NoticeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class CommentControllerTest {
    private MockMvc mockMvc1;
    private MockMvc mockMvc2;
    @Mock
    private NoticeService noticeService;
    @InjectMocks
    private NoticeController noticeController;
    @Mock
    private CommentService commentService;
    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc1 = MockMvcBuilders.standaloneSetup(noticeController).build();
        mockMvc2=MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    void save() throws Exception {
        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc1.perform(post("/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        Comment comment = new Comment(null, null, "test", "comment", "date");

        //저장
        when(commentService.save(any(Comment.class))).thenReturn(comment);
        comment.setNotice(notice);
        //체크
        mockMvc2.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

    }

    @Test
    void findAll() throws Exception{
        //생성
        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc1.perform(post("/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        //생성
        Comment comment1 = new Comment(null, null, "test", "comment1", "date");
        Comment comment2 = new Comment(null, null, "test", "comment2", "date");

        //저장
        when(commentService.findAll()).thenReturn(Arrays.asList(comment1, comment2));
        comment1.setNotice(notice);
        comment2.setNotice(notice);
        //체크
        mockMvc2.perform(get("/comment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findById() throws Exception{
        Long id = 1L;
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc1.perform(get("/notice/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Comment comment = new Comment(null, null, "test", "comment", "date");

        //저장
        when(commentService.save(any(Comment.class))).thenReturn(comment);
        comment.setNotice(notice);
        //체크
        mockMvc2.perform(get("/comment/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update() throws Exception{
        Long id = 1L;

        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc1.perform(post("/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        Comment existingComment = new Comment(id, notice, "test", "oldComment", "date");
        Comment updatedComment  = new Comment(id, notice, "test", "newComment", "date");

        when(commentService.findById(id)).thenReturn(Optional.of(existingComment));
        when(commentService.update(eq(id), any(Comment.class))).thenReturn(updatedComment);

        mockMvc2.perform(put("/comment/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contents\":\"newComment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents").value("newComment"));
    }
}