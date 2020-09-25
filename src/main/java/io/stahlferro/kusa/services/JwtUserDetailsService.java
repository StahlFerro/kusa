package io.stahlferro.kusa.services;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// It overrides the loadUserByUsername for fetching user details from the database using the username
@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserBaseService userBaseService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        UserBase user = userBaseService.getUserByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login name: " + loginName);
        }
        return new User(user.getLoginName(), user.getPasswordHash(), new ArrayList<>());
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("BAD CREDENTIALS", e);
        }
    }
}

