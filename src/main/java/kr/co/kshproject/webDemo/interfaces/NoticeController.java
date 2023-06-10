package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.NoticeService;
import kr.co.kshproject.webDemo.Domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(today);
        notice.setCreatedDate(dateString);

        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            notice.setUsername(user.getUsername());
        }

        String tmpContent=notice.getContents();
        String tmpEmail=notice.getEmail();
        String tmpTitle=notice.getTitle();

        if(tmpContent.length()>100) {
            tmpContent=tmpContent.substring(0, 100);
        }

        if(tmpEmail.length()>100){
            tmpEmail=tmpEmail.substring(0, 100);
        }

        if(tmpTitle.length()>100){
            tmpTitle=tmpTitle.substring(0, 100);
        }
        System.out.println("tmpContent"+tmpContent);
        notice.setContents(ConverterStringToHtml(tmpContent));
        notice.setEmail(ConverterStringToHtml(tmpEmail));
        notice.setTitle(ConverterStringToHtml(tmpTitle));
        try{
            noticeService.save(notice);
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
        System.out.println("redirect");
        return "forward:/index.html";
    }

    @GetMapping("/api/Detail/{id}")
    @ResponseBody
    public Notice GetApiNoticeDetail(@PathVariable Long id,HttpServletRequest request){
        Notice notice = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {

            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return null;
        }
        try{
            notice= noticeService.findDetail(id);
            notice.setContents(ConverterHtmlToStr(notice.getContents()));
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
            String tmpContent=newNotice.getContents();
            String tmpEmail=newNotice.getEmail();
            String tmpTitle=newNotice.getTitle();

            if(newNotice.getContents().length()>100) {
                tmpContent=tmpContent.substring(0, 100);
            }

            if(newNotice.getEmail().length()>100){
                tmpEmail=tmpEmail.substring(0, 100);
            }

            if(newNotice.getTitle().length()>100){
                tmpTitle=tmpTitle.substring(0, 100);
            }

            newNotice.setContents(ConverterStringToHtml(tmpContent));
            newNotice.setEmail(ConverterStringToHtml(tmpEmail));
            newNotice.setTitle(ConverterStringToHtml(tmpTitle));

            noticeService.update(id,newNotice);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    private String ConverterStringToHtml(String str){
        return str.replaceAll("[^\\w\\s\\p{L}]", "").replaceAll("\\n", "<br>");
    }

    private String ConverterHtmlToStr(String str){
        return str.replaceAll("<br>", "\n");
    }
    private String ConverterStringToHtmlEnter(String str){
        return str.replaceAll("\n", "<br>");
    }


}
