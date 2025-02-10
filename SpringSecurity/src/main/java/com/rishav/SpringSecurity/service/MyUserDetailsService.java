package com.rishav.SpringSecurity.service;

import com.rishav.SpringSecurity.model.UserPrincipal;
import com.rishav.SpringSecurity.model.Users;
import com.rishav.SpringSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=repo.findByUsername(username);
        if(user==null) {
            System.out.println("User not found!");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
