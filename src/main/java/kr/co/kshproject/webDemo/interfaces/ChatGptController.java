package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.ChatGptService;
import kr.co.kshproject.webDemo.Domain.ChatGpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ChatGptController {
    @Autowired
    private ChatGptService chatGptService;

    @PostMapping("/api/ChatGptApi")
    public ResponseEntity<String> ChatGptsPost(@RequestBody ChatGpt chatGpt, HttpServletRequest request) {
        String CHATGPT_API_KEY  = "";
        HttpSession session = request.getSession(false);
        String answer="연결을 실패하였습니다.";
        if (session == null || session.getAttribute("user") == null) {
           //  세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return  ResponseEntity.ok(answer);
        }

        try{
             CHATGPT_API_KEY=chatGpt.getApikey();
             answer=chatGptService.getChatResponse(chatGpt.getInput(),CHATGPT_API_KEY);
        }catch (Exception e){
            System.out.println("error");
            return  ResponseEntity.ok(answer);
        }
        return  ResponseEntity.ok(answer);
    }
}