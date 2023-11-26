package kr.co.kshproject.webDemo.interfaces.Admin;

import kr.co.kshproject.webDemo.Applicaiton.Admin.AdminService;
import kr.co.kshproject.webDemo.Applicaiton.Notice.NoticeServiceImpl;
import kr.co.kshproject.webDemo.Domain.Notice.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private NoticeServiceImpl noticeService;


    @GetMapping("/")
    public String getAdmin(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
    @GetMapping("/product")
    public String getAdminProductList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
    @GetMapping("/notice")
    public String getAdminNoticeList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }

    @GetMapping("/notice/{page}")
    public ResponseEntity<Map<String, List>>getAdminFindAllWithComments(@PathVariable int page){
       /*
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }*/

        return ResponseEntity.ok( noticeService.findAllWithComments(page));
    }

    @DeleteMapping("/notice/{id}")
    public String deleteAdminNoticeDetail(@PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        noticeService.deleteById(id);
        //noticeService.deleteNoticeDetail(id);
        return "redirect:/Admin/Post";
    }
    @GetMapping("/notice/{page}/{id}")
    public ResponseEntity<Notice> findWithCommentsById(@PathVariable int page, @PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }

        Optional<Notice> notice=noticeService.findWithCommentsById(page,id);

        return ResponseEntity.ok( notice.get() );
    }

    @DeleteMapping("/product/{id}")
    public String deleteAdminProductDetail(@PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "redirect:/index.html";
    }

    @GetMapping("/Admin/Post/Regist")
    public String getAdminPostRegist(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
    @GetMapping("/Admin/Customer")
    public String getAdminCustomer(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
}
