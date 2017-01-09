package me.jpomykala.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.jpomykala.model.user.User;
import me.jpomykala.service.user.UserService;

/**
 * Created by Evelan on 26/12/2016.
 */
@Slf4j
@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private String secret = "mySuperSecretToken";

    @NonNull
    private final UserService userService;

    @Autowired
    public TokenAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public User getUserFromToken(String token) {
        final String username = getUsernameFromToken(token);
        return userService.findByEmail(username);
    }
}