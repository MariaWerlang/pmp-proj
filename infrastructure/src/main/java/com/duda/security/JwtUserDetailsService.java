package com.duda.security;

import com.duda.entity.JwtToken;
import com.duda.entity.User;
import com.duda.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public JwtUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username){
        return JwtUtils.createToken(username);
    }

}
