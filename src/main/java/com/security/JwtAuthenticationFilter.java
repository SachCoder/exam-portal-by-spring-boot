package com.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Lazy
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwtToken = extractFromRequest(request);
//            System.out.println(jwtToken);
//            System.out.println(jwtTokenProvider.validateToken(jwtToken));
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                String id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
//                System.out.println(id);
                UserDetails user = userDetailsService.loadUserById(id);
//                System.out.println(user);
                if(user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }catch (Exception e) {
            return;
        }
        filterChain.doFilter(request, response);

    }

    private String extractFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length() + 1);
        return null;
    }

}
