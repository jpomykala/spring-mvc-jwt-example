package me.jpomykala.service.token;


import me.jpomykala.model.user.User;

/**
 * Created by Evelan on 26/12/2016.
 */
public interface TokenAuthenticationService {
    String getUsernameFromToken(String token);

    String createToken(User user);

    User getUserFromToken(String token);
}
