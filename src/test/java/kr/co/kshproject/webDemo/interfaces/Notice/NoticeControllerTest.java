package kr.co.kshproject.webDemo.interfaces.Notice;

import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NoticeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoticeService noticeService;

    @InjectMocks
    private NoticeController noticeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController).build();
    }

    @Test
    void save() throws Exception {
        //생성
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc.perform(post("/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        //생성
        Notice notice1= new Notice(null,"test1","title","cotenst","email","date",null);
        Notice notice2= new Notice(null,"test2","title","cotenst","email","date",null);

        //저장
        when(noticeService.findAll()).thenReturn(Arrays.asList(notice1, notice2));
        //체크
        mockMvc.perform(get("/notice"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findById() throws Exception {
        //생성
        Long id = 1L;
        Notice notice= new Notice(null,"test","title","cotenst","email","date",null);

        //저장
        when(noticeService.save(any(Notice.class))).thenReturn(notice);

        //체크
        mockMvc.perform(get("/notice/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update() throws Exception {
        /*
        Long id = 1L;
        Notice existingNotice = new Notice(id, "test","oldTitle", "oldContent", "oldEmail", "oldDate",null);
        Notice updatedNotice = new Notice(id,"test", "newTitle", "newContent", "oldEmail", "oldDate",null);

        when(noticeService.findWithCommentsById(id)).thenReturn(existingNotice);
        when(noticeService.update(eq(id), any(Notice.class))).thenReturn(updatedNotice);

        mockMvc.perform(put("/notice/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"newTitle\", \"contents\":\"newContent\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("newTitle"))
                .andExpect(jsonPath("$.contents").value("newContent"));*/
    }
}