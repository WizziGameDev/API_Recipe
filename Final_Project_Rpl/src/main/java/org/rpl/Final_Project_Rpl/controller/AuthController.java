package org.rpl.Final_Project_Rpl.controller;

import org.rpl.Final_Project_Rpl.dto.GetUserResponse;
import org.rpl.Final_Project_Rpl.dto.LoginUserRequest;
import org.rpl.Final_Project_Rpl.dto.TokenResponse;
import org.rpl.Final_Project_Rpl.dto.WebResponse;
import org.rpl.Final_Project_Rpl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(
            value = "/api/users/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login (@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @DeleteMapping(
            path = "/api/users/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<String> logOut(@RequestHeader(name = "X-API-TOKEN") String token) {
        authService.logOutUser(token);
        return WebResponse.<String>builder().data("OK").build();
    }
}
