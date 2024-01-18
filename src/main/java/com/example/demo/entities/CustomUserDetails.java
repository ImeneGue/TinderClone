package com.example.demo.entities;

import java.util.*;

import com.example.demo.entities.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<AccountType> accountTypes = user.getAccountType();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (AccountType accountType : accountTypes) {
            authorities.add(new SimpleGrantedAuthority(accountType.getAccountTypeName()));
        }
        return authorities;
    }


    public boolean hasAccountType(String AccountTypeName) {
        return this.user.hasAccountType(AccountTypeName);
    }

}
