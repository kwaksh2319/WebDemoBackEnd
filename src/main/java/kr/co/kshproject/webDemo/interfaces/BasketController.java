package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.BasketService;
import kr.co.kshproject.webDemo.Applicaiton.ProductService;
import kr.co.kshproject.webDemo.Applicaiton.UsersDetailService;
import kr.co.kshproject.webDemo.Domain.Baskets;
import kr.co.kshproject.webDemo.Domain.BasketsWithProduct;
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
public class BasketController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersDetailService usersDetailService;

    @GetMapping("/api/Basket")
    public ResponseEntity<?> GetBaskets(  HttpServletRequest request){
        //todo
        //int page,int size,HttpServletRequest request
        List<BasketsWithProduct> basketsWithProducts = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            //todo
            basketsWithProducts=basketService.findAll(user.getUsername(),"B",1,10);
        }
       if(basketsWithProducts.isEmpty()){
           return ResponseEntity.badRequest().build();
       }
        return ResponseEntity.ok(basketsWithProducts);
    }

    @PostMapping("/Basket/Post")
    public ResponseEntity<?> PostBaskets(@RequestBody Baskets baskets, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        //TODO
        //날짜 갯수카운팅 후 데이터 변경 상품, 유저아이디 체크할것
        try{
            basketService.save(baskets,session);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/Basket/Patch/{BindNumber}/{Status}")
    public ResponseEntity<?> updatePatch(@PathVariable String BindNumber,@PathVariable String Status,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        try{
            basketService.update(BindNumber,Status);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
