package com.example.shoppingmall.config;

import com.example.shoppingmall.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor

public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll() // ✅ 인증 제외 경로
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // ✅ 로그인 페이지 (GET 요청)
                        .loginProcessingUrl("/process-login") // ✅ 로그인 처리 URL (POST 요청)
                        .defaultSuccessUrl("/", true) // ✅ 로그인 성공 시 "/"로 이동
                        .failureUrl("/login?error") // ✅ 로그인 실패 시 "/login?error=true"로 이동
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // ✅ 로그아웃 처리 URL
                        .logoutSuccessUrl("/login?logout") // ✅ 로그아웃 성공 시 "/login?logout"으로 이동
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)  // ✅ 기존 세션 유지
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ 비밀번호 암호화 설정
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
