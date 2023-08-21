package kr.co.kshproject.webDemo.Common;

import kr.co.kshproject.webDemo.Applicaiton.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
   //78678678687
    @Autowired
    @Lazy
    private AuthenticationProvider myAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/Admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/Main/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/main/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/Products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/PostList/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/postList/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/PostDetail/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/postDetail/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/PostModify/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/postModify/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/Api/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/api/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/logout/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/ChatGptApi/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOM")
                .antMatchers("/SignUp/**").permitAll()
                .antMatchers("/").permitAll()

                .anyRequest().permitAll()
                .and()
                .headers().frameOptions().disable() // H2 콘솔 사용을 위해 X-Frame-Options 비활성화
                .and()
                .csrf().disable(); // H2 콘솔과의 CSRF 충돌 방지
        http.authenticationProvider(myAuthenticationProvider);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
         //사용자 정보 로딩 로직을 포함한 UserDetailsService 빈 생성
        return new UsersDetailService();
    }
    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        private AuthenticationProvider myAuthenticationProvider;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(myAuthenticationProvider);
        }
    }

    @Bean
    public AuthenticationProvider myAuthenticationProvider() {
        return new MyAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new CustomAuthenticationManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

