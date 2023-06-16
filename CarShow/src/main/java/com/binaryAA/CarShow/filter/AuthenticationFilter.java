package com.binaryAA.CarShow.filter;

import com.binaryAA.CarShow.jwtService.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get token from header of the request
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(jwtToken);

        if(jwtToken != null) {

        String user = jwtService.getAuthUser(request);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

        // Authenticate the user
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);

        }

        filterChain.doFilter(request, response);
    }

}
