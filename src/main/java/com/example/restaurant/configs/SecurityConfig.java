package com.example.restaurant.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(
                new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/category/add").hasAuthority("ADMIN")
                                .requestMatchers("/category/update-by-id").hasAuthority("ADMIN")
                                .requestMatchers("/user/get-all-user").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .permitAll()
                ).httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.failureHandler((request, response, exception) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType("application/json");
                            response.getWriter().print("{\"message\": \"" + exception.getMessage() + "\"}");
                        }).successHandler((request, response, authentication) -> {
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json");
                            response.getWriter().print("{\"message\": \"Login successful\"}");
                        })
                ).exceptionHandling(handler -> handler.accessDeniedHandler(
                        (request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json");
                            response.getWriter()
                                    .print(String.format("{\"message\": \"%s\"}",
                                            accessDeniedException.getMessage()));
                        }))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .addLogoutHandler(clearSiteData)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpStatus.OK.value());
                            response.setContentType("application/json");
                            response.getWriter().print("{\"message\": \"Logout successful\"}");
                        })
                );

        return httpSecurity.build();
    }
}
