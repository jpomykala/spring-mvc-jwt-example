package me.jpomykala.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import me.jpomykala.rest.controller.form.AuthForm;
import me.jpomykala.model.user.User;
import me.jpomykala.model.user.view.UserView;
import me.jpomykala.service.token.TokenAuthenticationService;
import me.jpomykala.service.user.UserService;

/**
 * Created by Evelan on 27/12/2016.
 */
@Slf4j
@RestController
public class AuthenticationController {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(TokenAuthenticationService tokenAuthenticationService, UserService userService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userService = userService;
    }

    @PostMapping("/auth/sign")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(UserView.FullView.class)
    public User authorizeUser(@RequestBody AuthForm authForm) throws AuthenticationException {
        String email = authForm.getEmail();
        User user = userService.findByEmail(email);
        if (user == null) {
            user = fetchDataFromProviderResourceServer(authForm);
            user = userService.register(user);
        }
        final String token = tokenAuthenticationService.createToken(user);
        user.setToken(token);
        return user;
    }

    private User fetchDataFromProviderResourceServer(AuthForm authForm) {
        return User.builder()
                .email(authForm.getEmail())
                .firstName(authForm.getFirstName())
                .enabled(Boolean.TRUE) //  MOCKED
                .build();
    }

}
