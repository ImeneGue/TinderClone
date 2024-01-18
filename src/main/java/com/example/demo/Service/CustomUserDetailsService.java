package com.example.demo.Service;

import com.example.demo.entities.AccountType;
import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.User;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    private User user;

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<AccountType> roles = User.getAccountType();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (AccountType role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getAccountTypeName()));
//        }
//
//        return authorities;
//    }
}
