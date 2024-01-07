package kr.co.kshproject.webDemo.Common;

import kr.co.kshproject.webDemo.Applicaiton.JWT.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   //78678678687

    //@Autowired
    //private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomUserDetailsService userDetailsService;



    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private String secretKey="MySecretKey123";
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()

               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               // .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/favicon.ico").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/createtoken").permitAll()
                .antMatchers("/user/auth/**").permitAll()
                .antMatchers( "/Intro", "/").permitAll()//인증없이 허용
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()  .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(secretKey), UsernamePasswordAuthenticationFilter.class);


        log.info("spring security filter");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Spring security : PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

}

