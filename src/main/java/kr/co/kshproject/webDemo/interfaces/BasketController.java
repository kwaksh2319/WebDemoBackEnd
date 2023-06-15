package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.BasketService;
import kr.co.kshproject.webDemo.Applicaiton.ProductService;
import kr.co.kshproject.webDemo.Applicaiton.UsersDetailService;
import kr.co.kshproject.webDemo.Domain.Baskets;
import kr.co.kshproject.webDemo.Domain.BasketsWithProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        System.out.println(basketsWithProducts.get(0).getBaskets().getBindNumber());
        System.out.println(basketsWithProducts.get(0).getProduct().getImageUrl());

        return ResponseEntity.ok(basketsWithProducts);
    }

    @PostMapping("/Basket/Post")
    public ResponseEntity<?> PostBaskets(@RequestBody Baskets baskets, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }

        //유저
        String userId="";
        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            // notice.setUsername(user.getUsername());
            userId=user.getUsername();
        }
        baskets.getBasketId().setUsersId(userId);

        //날짜
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        baskets.getBasketId().setDate(formattedDateTime);

        //bindNumber
        baskets.setBindNumber(userId+baskets.getBindNumber());

        //TODO
        //날짜 갯수카운팅 후 데이터 변경 상품, 유저아이디 체크할것
        try{
            basketService.save(baskets);
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
