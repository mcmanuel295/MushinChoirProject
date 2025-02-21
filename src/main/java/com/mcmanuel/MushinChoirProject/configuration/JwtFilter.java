package com.mcmanuel.MushinChoirProject.configuration;

import com.mcmanuel.MushinChoirProject.service.JwtService;
import com.mcmanuel.MushinChoirProject.service.MyUserDetailsService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final MyUserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, MyUserDetailsService userDetailsService ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull FilterChain filterChain) throws ServletException, IOException {

        String header =request.getHeader("Authorize");
        String token =null;
        String username ;

        if (header !=null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.validate(token,userDetails)) {
                Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
