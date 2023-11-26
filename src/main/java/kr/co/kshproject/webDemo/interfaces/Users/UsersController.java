package kr.co.kshproject.webDemo.interfaces.Users;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UsersController {
    private final UserService usersService;


    @Autowired
    public UsersController(UserService usersService){
        this.usersService=usersService;
    }

    @Operation(summary = "Get all items", description = "Retrieve a list of items")
    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users user){
        return ResponseEntity.ok(usersService.save(user));
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.ok( usersService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<Users>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( usersService.findAll(page,size) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody UsersDTO usersDTO){
        return ResponseEntity.ok(usersService.update(id,usersDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        usersService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        usersService.deleteAll();
    }

}
