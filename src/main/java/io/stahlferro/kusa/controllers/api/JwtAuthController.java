package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.models.JwtResponse;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.security.JwtTokenUtil;
import io.stahlferro.kusa.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/api/auth")
public class JwtAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/register")
    // Accepts a username and password, returns a token if matches with the one in JwtUserDetailsService
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody UserBaseDto dto) throws Exception {
        userDetailsService.authenticate(dto.getLoginName(), dto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getLoginName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(token);
        return ResponseEntity.ok(new JwtResponse(token, expirationDate));
    }
}
