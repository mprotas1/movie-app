package com.protas.movieapp.filter;

import com.protas.movieapp.utils.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class.getName());
    @Value(value = "${movieapp.jwt.authorization}")
    private String AUTH_HEADER;
    @Value(value = "${movieapp.jwt.bearer}")
    private String BEARER_PREFIX;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userAuthService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTH_HEADER);

        if(authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            // TODO: should throw exception about incorrect token
            logger.error("The headers does not contain 'Bearer ' starting param or authorization header is null");
            return;
        }

        final String jwt = authorizationHeader.substring(BEARER_PREFIX.length());
        String email = jwtUtils.extractEmail(jwt);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userAuthService.loadUserByUsername(email);

            if(jwtUtils.isTokenValid(jwt, user)) {
                setUpUserAuthentication(request, user);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setUpUserAuthentication(HttpServletRequest request, UserDetails user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                null,
                user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
