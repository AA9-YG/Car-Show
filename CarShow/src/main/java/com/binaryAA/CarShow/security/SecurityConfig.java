package com.binaryAA.CarShow.security;

import com.binaryAA.CarShow.exception.AuthEntryPoint;
import com.binaryAA.CarShow.filter.AuthenticationFilter;
import com.binaryAA.CarShow.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationConfiguration configuration;

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Autowired
    private AuthEntryPoint exceptionHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.
                csrf().disable().cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth-> {
                    auth.requestMatchers(HttpMethod.POST,"/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/v1/car/*").permitAll();
                    try {
                        auth.anyRequest().authenticated()
                                .and()
                                .exceptionHandling()
                                .authenticationEntryPoint(exceptionHandler)
                                .and()
                                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

//        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
//            auth.requestMatchers(HttpMethod.POST, "api/v1/car").hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.GET).permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .addFilterBefore(authenticationFilter, AuthenticationFilter.class);}).httpBasic(Customizer.withDefaults())
//                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // This below is deprecated
//                httpSecurity.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers(HttpMethod.POST, "api/v1/car")
//                .hasRole("ADMIN")
//                .requestMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .requestMatchers(HttpMethod.GET).permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        return httpSecurity.build();
//
//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET).permitAll()
//                .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(authenticationFilter, AuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return httpSecurity.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(""));
        config.setAllowedMethods(Arrays.asList(""));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

}
