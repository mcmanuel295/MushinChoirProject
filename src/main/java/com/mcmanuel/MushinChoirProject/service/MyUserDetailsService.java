package com.mcmanuel.MushinChoirProject.service;

import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(username)
                .orElseThrow(()->
                        new UsernameNotFoundException("User with username "+username+" not found"));

        return new MyUserDetails(user);
    }
}
