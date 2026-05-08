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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
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
                csrf(csrf -> csrf.ignoringRequestMatchers("/signUp", "/signUpPage", "/api/signIn", "/api/signUp", "/logout", "/api/views/**"))
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
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/signIn?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/oauth2/authorization/google","/changePassword","/restore", "/restorePassword","/signUp",
                                "/signUpPage", "/signIn", "/css/**", "/images/**", "/js/**", "/css/*.css", "/api/signIn", "/api/signUp", "/comments/**", "/api/views/**")
                        .permitAll()
                        .requestMatchers("/home/**", "/teachers", "/sendCode").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/admin/**", "/adminPage/**").hasRole("ADMIN")
                        .requestMatchers("/user", "/user/**", "/logout", "/api/sms/**").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/signIn")
                        )
                        .accessDeniedHandler(((request, response, accessDeniedException) ->
                                response.sendRedirect("/access-denied"))
                        )
                );

        return http.build();
    }
}
