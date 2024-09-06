package com.cows.config;

import javax.sql.DataSource;
import com.cows.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.beans.factory.annotation.Value; 
import java.util.Arrays;
import org.springframework.context.annotation.Profile; 

@Configuration
@EnableWebSecurity
@Profile({"dev", "prod"})
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource()) // 使用新的 CORS 配置
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/login", "/login/wechat", "/login/wechat/callback", 
                                 "/login/perform_login", "/v1/users/addUser", "/wechatLogin.html", 
                                 "/login/wechat/verify", "/location", "/test.png", 
                                 "/v1/getLatestImage", "/v1/carousels/getAllCarousels", 
                                 "/v1/productCategories/getAllProductCategories", 
                                 "/v1/productsCarousels/getAllProductsCarousels", 
                                 "/v1/products/getAllProducts", 
                                 "/v1/products/getAllProductsCarousels", 
                                 "/MP_verify_tSDyEHEKTxbBXHMd.txt", "/v1/admins/login", 
                                 "/v1/basicInformation/getAllBasicInformation", 
                                 "/v1/carousels/getAllCarousels", 
                                 "/v1/products/getAllProducts", 
                                 "/v1/products/getProductById/*",
                                 "/v1/businesses/getBusinessById/1",
                                 "/v1/businesses/getAllBusinesses", 
                                 "/v1/certificates/getAllCertificates", 
                                 "/v1/abouts/getAboutById/1")
                .permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            )
            .csrf().disable(); // 禁用 CSRF 保护
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins)); // 更新此行
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}