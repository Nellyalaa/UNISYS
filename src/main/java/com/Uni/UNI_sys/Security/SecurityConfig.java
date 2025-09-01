package com.Uni.UNI_sys.Security;

import com.Uni.UNI_sys.Filter.CustomAuthorizationFilter;
import com.Uni.UNI_sys.Filter.customAuthFilter;
import com.Uni.UNI_sys.domain.Admin;
import com.Uni.UNI_sys.domainrepo.Adminrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Adminrepo adminRepo;

    // UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Admin found = adminRepo.findByUsername(username);
            if (found == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            return found;
        };
    }

    //  Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //  Authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //  Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        customAuthFilter customAuth = new customAuthFilter(authManager);
        customAuth.setFilterProcessesUrl("/api/login");


        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()


                        .requestMatchers(HttpMethod.GET, "/api/v1/Stu")
                        .hasAuthority("VIEW_STUDENT")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/Stu/**")
                        .hasAuthority("EDIT_STUDENT")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/Stu/**")
                        .hasAuthority("DELETE_STUDENT")

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilter(customAuth) // login filter
                .addFilterBefore(new CustomAuthorizationFilter(), customAuthFilter.class)
                .sessionManagement(session -> session.disable());

        return http.build();
    }

}
