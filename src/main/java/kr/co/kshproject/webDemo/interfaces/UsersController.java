package kr.co.kshproject.webDemo.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class UsersController {

/*
    @Autowired
    private UsersServices usersServices; //서비스

    //user 여러개 조회  ( 관리자 계정: 유저리스트 조회 )
    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return usersServices.getAllUsers();
    }

    //user 한개 조회  ( 관리자 계정: 유저리스트 조회 )
    @GetMapping("/users/{id}")
    public Users getUsers(@PathVariable("id") String id){
        Users users=usersServices.getUsers(id);
        return users;
    }
    //유저 접속
//
    @PostMapping("/signIn")
    public ResponseEntity<?> SignIn(@Valid @RequestBody Users resource) throws URISyntaxException {
        Users users = usersServices.loginUsers(
                Users.builder()
                        .id(resource.getId())
                        .password(resource.getPassword())
                        .build());

        URI location = new URI("/main");
        return ResponseEntity.created(location).body("{}");
    }

        //user 접속        ( 로그인 )
        @PostMapping("/SignIn")
        public ResponseEntity<?> logins(@Valid @RequestBody Users resource) throws URISyntaxException {
            //return usersServices.loginUsers(resource);
            /*
            Users users = usersServices.loginUsers(
                    Users.builder()
                            .id(resource.getId())
                            .password(resource.getPassword())
                            .build());

        URI location = new URI("/SignUp");
        return ResponseEntity.created(location).body("{}");
    } */
    //user 등록       ( 회원가입 )
//등록

}
