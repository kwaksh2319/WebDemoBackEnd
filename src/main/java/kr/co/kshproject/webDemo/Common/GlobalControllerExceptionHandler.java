package kr.co.kshproject.webDemo.Common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @GetMapping("/fail")
    public ResponseEntity<?> loginFail(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
    }


    /*
    @GetMapping("/")
    public String loginInit(HttpServletRequest request) {

        return "forward:/index.html";
    }*/
}
