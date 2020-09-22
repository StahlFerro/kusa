package io.stahlferro.kusa.services;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
// It overrides the loadUserByUsername for fetching user details from the database using the username, but for now it
// fetches a hardcoded user list with a predefined user (username: pub, password: password)
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserBaseService userBaseService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        UserBase user = userBaseService.getUserByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login name: " + loginName);
        }
        return new User(user.getLoginName(), user.getPasswordHash(), new ArrayList<>());
    }
}

