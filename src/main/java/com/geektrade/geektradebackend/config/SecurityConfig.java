package com.geektrade.geektradebackend.config;

import com.geektrade.geektradebackend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfig {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/configuration/ui",
                "/webjars/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/configuration/security",
                "/v2/api-docs")
                .requestMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/",
                                "/home",
                                "/js/**",
                                "/css/**",
                                "/img/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v2/**",
                                "/swagger-resources/**",
                                "/api/user/signup",
                                "/api/user/login",
                                "/api/categories",
                                "/api/user/profile-image/**",
                                "/api/listings/**",
                                "/api/listings"
                        ).permitAll()
                )
                .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
                .logout(LogoutConfigurer::permitAll);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public AuthenticationManagerBuilder configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
//        return auth;
//    }
}
