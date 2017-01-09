package me.jpomykala.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import me.jpomykala.model.user.User;
import me.jpomykala.model.user.view.UserView;

/**
 * Created by evelan on 07/01/2017.
 */
@Slf4j
@RestController
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    @JsonView(UserView.FullView.class)
    public User getUser(
            @RequestHeader(name = "Authorization") String jwtToken, //just for Swagger
            @AuthenticationPrincipal User user) {
        return user;
    }

}
