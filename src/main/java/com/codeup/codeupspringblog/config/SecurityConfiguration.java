package com.codeup.codeupspringblog.config;

import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page

                /* Pages that require authentication */
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers(
                        "/posts/create", // only authenticated users can create posts
                        "/posts/{id}/edit" // only authenticated users can edit posts

                )

                .authenticated()
                /* Logout configuration */
                .and()
                    .logout()
                    .logoutSuccessUrl("/login") // append a query string value
                    /* Pages that can be viewed without having to log in */
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/", "/posts", "/posts/{id}", "/sign-up", "/css/**","/about") // anyone can see home, the posts pages, and sign up
                    .permitAll()

                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/**")
                    .permitAll()
        ;
        return http.build();
    }

}

