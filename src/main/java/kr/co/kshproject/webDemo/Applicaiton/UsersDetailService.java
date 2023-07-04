package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Users;
import kr.co.kshproject.webDemo.Domain.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UsersDetailService implements UserDetailsService {

    @Autowired
    private  UsersDao usersDao;
    public UsersDetailService(){

    }
    public UsersDetailService(UsersDao usersDao){
        this.usersDao=usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //유저 데이터 가져오기
        Users user = usersDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } //user.getLevel()
        // 사용자 정보를 UserDetails 객체로 변환하여 반환user.getRoles()
        String role = "ROLE_ADMIN";
        if(user.getLevel()==0){
            role = "ROLE_ADMIN";
        }else if(user.getLevel()==1){
            role = "ROLE_CUSTOM";
        }else{
            role = "";
        }

        List<String> roles=new LinkedList<>();
        roles.add(role);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(roles)
        );
    }

    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        // 사용자의 권한(role) 정보를 GrantedAuthority 객체로 변환합니다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
