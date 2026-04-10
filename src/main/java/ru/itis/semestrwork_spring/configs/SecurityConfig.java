package ru.itis.semestrwork_spring.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itis.semestrwork_spring.security.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(CsrfConfigurer::disable)
                .formLogin((form) -> form
                        .loginPage("/signIn")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user", true)
                        .failureUrl("/signIn?error")
                        .permitAll()
                )
                .oauth2Login((auth) -> auth.loginPage("/signIn")
                        .defaultSuccessUrl("/user")
                        .failureUrl("/signIn?error")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .permitAll()
                )
                .logout((logout) -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/signIn?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home/**", "/teachers").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/admin/**", "/adminPage/**").hasRole("ADMIN")
                        .requestMatchers("/user", "/user/**").authenticated()
                        .requestMatchers("/oauth2/authorization/google","/changePassword","/restore", "/restorePassword","/signUp",
                                "/signUpPage", "/signIn", "/css/**", "/images/**", "/*.js", "/*.css")
                        .permitAll()
                        .anyRequest().authenticated()
                );
                //  .exceptionHandling((ex) -> ex.accessDeniedPage("/exceptions/error_403"));

        return http.build();
    }
}
