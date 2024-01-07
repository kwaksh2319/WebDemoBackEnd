package kr.co.kshproject.webDemo.interfaces.Users;

import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Common.AuthApplication;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UsersController {
    private final UserService usersService;
    private final AuthApplication authApplication;

    @Autowired
    public UsersController(UserService usersService, AuthApplication authApplication){
        this.usersService=usersService;
        this.authApplication = authApplication;
    }

    @PostMapping
    public ResponseEntity<Users> save(@Validated @RequestBody UsersDTO usersDTO){
       // usersService.save(usersDTO)
        return ResponseEntity.ok(  authApplication.signup(usersDTO) );
    }

    @PostMapping("/auth/createtoken")
    public ResponseEntity<Users> savetoken(@Validated @RequestBody UsersDTO usersDTO){
        // usersService.save(usersDTO)
        return ResponseEntity.ok(  authApplication.signup(usersDTO) );
    }
    @GetMapping("/auth/securitytest")
    public ResponseEntity<?> securityTest(Authentication authentication){
        String getName = authentication.getName();
        return ResponseEntity.ok(getName + " Authentication에서 인증인 확인되었습니다");
    }

    @GetMapping("/auth/createtoken")
    public ResponseEntity<String> getToken(@Validated @RequestBody UsersDTO usersDTO){
        // usersService.save(usersDTO)
        try {
            String jwtToken = authApplication.signin(usersDTO);
            if (jwtToken != null) {
                return ResponseEntity.ok(jwtToken);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll(){
        return ResponseEntity.ok( usersService.findAll() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> findById(@Min(1) @PathVariable Long id){
        return ResponseEntity.ok( usersService.findById(id) );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( usersService.findAll(page,size) );
    }

    @PutMapping("/{id}")
    public void update(@Min(1) @PathVariable Long id, @Validated @RequestBody UsersDTO usersDTO){
         usersService.update(id,usersDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
        usersService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        usersService.deleteAll();
    }

}
