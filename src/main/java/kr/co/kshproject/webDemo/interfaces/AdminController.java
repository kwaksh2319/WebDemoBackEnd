package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.AdminService;
import kr.co.kshproject.webDemo.Applicaiton.NoticeService;
import kr.co.kshproject.webDemo.Applicaiton.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ProductService productService;

    @GetMapping("/Admin")
    public String getAdmin(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
    @GetMapping("/Admin/Product")
    public String getAdminProductList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        System.out.println("/Admin/Product");
        return "forward:/index.html";
    }
    @GetMapping("/Admin/Post")
    public String getAdminNoticeList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        System.out.println("/Admin/Post");
        return "forward:/index.html";
    }

    @DeleteMapping("/Admin/Notice/Delete/{id}")
    public String deleteAdminNoticeDetail(@PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        System.out.println("id:"+id);
        noticeService.deleteNoticeDetail(id);
        System.out.println("/Admin/Notice/Delete/");
        return "redirect:/Admin/Post";
    }

    @DeleteMapping("/Admin/Product/Delete/{id}")
    public String deleteAdminProductDetail(@PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        productService.deleteProductDetail(id);
        System.out.println("/Admin/Product/Delete/");
        return "redirect:/index.html";
    }

    @GetMapping("/Admin/Post/Regist")
    public String getAdminPostRegist(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        System.out.println("/Admin/Post/Regist");
        return "forward:/index.html";
    }
    @GetMapping("/Admin/Customer")
    public String getAdminCustomer(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("not success");
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        System.out.println("/Admin/Customer");
        return "forward:/index.html";
    }
}
