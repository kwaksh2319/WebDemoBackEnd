package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @PostMapping("/Notice/Post")
    public ResponseEntity<?> PostNotice(@RequestBody Notice notice, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        try{
            noticeService.save(notice,session);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/api/Notice")
    @ResponseBody
    public List<Notice> GetApiNotice(HttpServletRequest request){
        List<Notice> notices = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return notices;
        }

        try{
            notices = noticeService.findAll(1,10);
        }catch (Exception e){
            e.printStackTrace();
        }
        return notices;
    }

    @GetMapping("/Notice")
    public String GetNotice(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }

    @GetMapping("/api/Detail/{id}")
    @ResponseBody
    public Notice GetApiNoticeDetail(@PathVariable Long id,HttpServletRequest request){
        Notice notice = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return null;
        }
        try{
            notice= noticeService.findDetail(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return notice;
    }

    @PatchMapping("/api/Notice/Patch/{id}")
    public ResponseEntity<?> updatePatch(@PathVariable Long id, @RequestBody Notice newNotice,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }

        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            if(user.getUsername().equals(newNotice.getUsername())==false){
                return ResponseEntity.badRequest().build();
            }
        }

        try{
            noticeService.update(id,newNotice);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
