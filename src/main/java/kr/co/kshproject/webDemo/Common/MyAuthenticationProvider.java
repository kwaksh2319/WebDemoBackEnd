package kr.co.kshproject.webDemo.Common;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;


public class MyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 사용자 인증 로직 수행
        if (username.equals("admin") && password.equals("password")) {
            // 인증 성공 시, 인증된 Authentication 객체 반환
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        } else {
            // 인증 실패 시, 예외 발생
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        // 지원하는 Authentication 타입을 명시
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}
