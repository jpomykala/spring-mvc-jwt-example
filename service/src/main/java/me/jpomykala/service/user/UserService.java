package me.jpomykala.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import me.jpomykala.model.user.User;

/**
 * Created by evelan on 12/21/15.
 */
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User register(User user);

}
