package com.cafetron.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * MODULE 1 (Identity & Access).
 * Reads the Bearer token, validates it via JwtService, and populates the
 * SecurityContext with the authenticated user + role authorities.
 *
 * TODO (Module 1 owner): load the user via UserDetailsService, build an
 * UsernamePasswordAuthenticationToken with role authorities, and set it on
 * SecurityContextHolder.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        // TODO: extract username, validate, and set authentication in the context.
        // String username = jwtService.extractUsername(token);
        // ...

        filterChain.doFilter(request, response);
    }
}
