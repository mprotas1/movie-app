package com.protas.movieapp.service;

import com.protas.movieapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserAuthService.class.getName());
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("I'm loading UserDetails by email: {}", username);
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Could not find the appropriate entity with email: " + username));
    }
}
