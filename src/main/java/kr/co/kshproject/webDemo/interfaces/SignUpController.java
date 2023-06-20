package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.SignUpService;
import kr.co.kshproject.webDemo.Domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignUpController {

    //1.user list
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/SignUp")
    public String SignUpUser(){
        System.out.println("SignUp spring");
        return "forward:/index.html";
    }

    @PostMapping("/SignUp")
    public ResponseEntity<?> SignUp(@RequestBody Users usersData){
        try{
            signUpService.registerUser(usersData);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
