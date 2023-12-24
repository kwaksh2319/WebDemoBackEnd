package kr.co.kshproject.webDemo.interfaces.Users;

import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public UsersController(UserService usersService){
        this.usersService=usersService;
    }

    @PostMapping
    public ResponseEntity<Users> save(@Validated @RequestBody UsersDTO usersDTO){
        return ResponseEntity.ok(usersService.save(usersDTO));
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
