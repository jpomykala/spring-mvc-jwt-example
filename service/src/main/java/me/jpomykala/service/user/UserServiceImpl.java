package me.jpomykala.service.user;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import me.jpomykala.model.user.User;
import me.jpomykala.model.user.UserRole;
import me.jpomykala.repository.user.UserRepository;

import java.util.Optional;

/**
 * Created by Evelan-E6540 on 06/09/2015.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return findByEmail(username);
    }

    @Override
    public User register(User user) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("User exists by email");
        }

        user.setUserRoles(Sets.newHashSet(UserRole.ROLE_USER));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
