package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.ProductService;
import kr.co.kshproject.webDemo.Domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ProductController {

    //1.user list
    @Autowired
    private ProductService productService;

    @GetMapping("/Product")
    public String GetProduct(@RequestParam(value = "param", required = false) String imageUrl , HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }

    @GetMapping("/api/Products")
    @ResponseBody
    public List<Product> GetApiProducts(@RequestParam(value = "param", required = false) String imageUrl,HttpServletRequest request ){
        List<Product> products = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return products;
        }
        try{
            products = productService.findAll(1,10);
        }catch (Exception e){
            e.printStackTrace();
            //TODO 예외처리 부분
            products = new ArrayList<>();
            Product tmpProduct = new Product();
            tmpProduct.setProductName("");
            tmpProduct.setImageUrl("https://via.placeholder.com/350x150");
            tmpProduct.setVideoUrl("https://via.placeholder.com/350x150");
            tmpProduct.setDescription("");
            tmpProduct.setPrice(BigDecimal.valueOf(0));
             for(int i=0;i<10;i++) {
                 products.add(tmpProduct);
             }
        }
        return products;
    }
    @GetMapping("/api/DetailProduct/{id}")
    @ResponseBody
    public Product GetApiProductDetail(@PathVariable Long id,HttpServletRequest request){
        Product product = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return product;
        }
        try{
            product= productService.findDetail(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping("/Products")
    public String GetProducts(){
        return  "forward:/index.html";
    }

    @PostMapping("/Products/Post")
    public ResponseEntity<?> PostProducts(@RequestBody Product product,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/Products/Patch/{id}")
    public ResponseEntity<?> updatePatch(@PathVariable Long id, @RequestBody Product newProduct,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return ResponseEntity.badRequest().build();
        }
        try{
            productService.update(id,newProduct);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
