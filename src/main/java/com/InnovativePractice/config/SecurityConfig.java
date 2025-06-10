package com.InnovativePractice.config;

import com.InnovativePractice.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security 配置类
 * 用于配置安全相关的功能
 */
@Configuration
@EnableWebSecurity  // 启用Spring Security的Web安全支持
public class SecurityConfig {
    
    /**
     * 配置安全过滤链
     * @param http HttpSecurity对象，用于配置安全规则
     * @param jwtAuthenticationFilter 自定义的JWT认证过滤器
     * @return 配置好的SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
            // 禁用CSRF保护，因为我们使用JWT进行认证
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // 添加CORS配置
            // 配置会话管理，设置为无状态
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 配置请求授权规则
            .authorizeHttpRequests(auth -> auth
                // 允许登录、注册和聊天接口的访问
                .requestMatchers("/user/login", "/user/register", 
                               "/user/chatFlux", "/user/chatO").permitAll()
                // 其他所有请求都需要认证
                .anyRequest().authenticated()
            )
            // 添加JWT过滤器到UsernamePasswordAuthenticationFilter之前
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    /**
     * 配置密码编码器
     * @return BCryptPasswordEncoder实例，用于密码的加密和验证
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
} 