package kz.abylay.example.config;


import kz.abylay.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.beans.BeanProperty;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.GET, "/authorize/**", "/error", "/logout", "/user/add-user").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/", "/table-cars", "/bmw-information", "/mercedes-information", "/audi-information", "/porsche-information").hasAnyRole("USER", "ADMIN", "MODERATOR");
                    authConfig.requestMatchers(HttpMethod.GET, "/add-marketplace", "/remove-marketplace", "/add-cars", "/add-cars-page", "/update/{id}", "/update-cars", "/delete", "/admins-panel").hasAnyRole("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/add-marketplace", "/add-cars", "/add-cars-page", "/update/{id}", "/update-cars").hasAnyRole("MODERATOR", "ADMIN");

                    authConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                            login.loginPage("/authorize/login");
                            login.usernameParameter("email");
                            login.passwordParameter("password");
                            login.loginProcessingUrl("/auth").permitAll();
                            login.defaultSuccessUrl("/");
                            login.failureForwardUrl("/error");
                        }
                ).logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.logoutSuccessUrl("/authorize/login");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                })
                .exceptionHandling(exception -> exception.accessDeniedPage("/error"));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("registration-css.css",
                "bootstrap-grid.min.css",
                "bootstrap-reboot.min.css",
                "style.css",
                "/js/**",
                "/images/**");
    }
}
