package kr.co.kshproject.webDemo.interfaces.Login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/login")
public class loginController {

   /*
    private final AuthenticationManager authenticationManager;
    //private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public loginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // JwtTokenProvider jwtTokenProvider
        //this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    private UsersDetailService usersDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IpAddressService ipAddressService;

    //1.user list
    @PostMapping("/Main/Logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build(); // 로그아웃 후 리다이렉트할 경로
    }*/
   /*
    @PostMapping("/loginProxy")
    public ResponseEntity<?> loginUser(@RequestBody Users usersData, HttpSession session, HttpServletRequest request){
        String hashedPassword=passwordEncoder.encode(usersData.getPassword());
        String ipAddress = request.getRemoteAddr();
        UserDetails userDetails = usersDetailService.loadUserByUsername(usersData.getUsername());

        if(passwordEncoder.matches(usersData.getPassword(),userDetails.getPassword())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            if (authentication.isAuthenticated()) {
                // 인증된 사용자 정보를 세션에 저장하거나 다른 처리를 수행할 수 있습니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
                session.setAttribute("user", userDetails);
                ipAddressService.save(ipAddress);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }
    }*/

    @GetMapping("/Main")
    public String loginUser(HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/fail";
        }
        return "forward:/index.html";
    }
}
