package kr.co.kshproject.webDemo.interfaces;

import kr.co.kshproject.webDemo.Applicaiton.SignUpService;
import kr.co.kshproject.webDemo.Domain.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SignUpController {
    //1.user list
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/SignUp")
    public String SignUpUser(){
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
