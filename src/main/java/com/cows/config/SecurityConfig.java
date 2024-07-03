package com.cows.config;

import javax.sql.DataSource;

import com.cows.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)// 添加JWT过滤器
            .authorizeRequests(authorize -> authorize
                .requestMatchers("/",
                        "/home",
                        "/login",
                        "/login/wechat",
                        "/login/wechat/callback",
                        "/login/perform_login",
                        "/v1/users/addUser",
                        "/wechatLogin.html",
                        "/login/wechat/verify",
                        "/location",
                        "/test.png",
                        "/v1/getLatestImage",
                        "/v1/carousels/getAllCarousels",
                        "/v1/productCategories/getAllProductCategories",
                        "/v1/productsCarousels/getAllProductsCarousels",
                        "/v1/products/getAllProducts",
                        "/v1/products/getAllProductsCarousels",
                        "/MP_verify_tSDyEHEKTxbBXHMd.txt",
                        "/v1/admins/login",
                        "/v1/basicInformation/getAllBasicInformation").permitAll()// 允许未认证访问
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
            .csrf().disable();  // 如果你使用POST方法，可能需要禁用CSRF保护
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setDataSource(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select userName, password, isEnable from User where userName=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select userName, 'ROLE_USER' from User where userName=?");
        return userDetailsManager;
    }
}