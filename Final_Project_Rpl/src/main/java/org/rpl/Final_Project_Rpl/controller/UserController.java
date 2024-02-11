package org.rpl.Final_Project_Rpl.controller;

import lombok.extern.slf4j.Slf4j;
import org.rpl.Final_Project_Rpl.dto.GetUserResponse;
import org.rpl.Final_Project_Rpl.dto.RegisterUserRequest;
import org.rpl.Final_Project_Rpl.dto.WebResponse;
import org.rpl.Final_Project_Rpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(
            path = "/api/users/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest userRequest) {
        userService.register(userRequest);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<GetUserResponse> getUser(@RequestHeader(name = "X-API-TOKEN") String token) {
        GetUserResponse responUser = userService.getUser(token);
        return WebResponse.<GetUserResponse>builder().data(responUser).build();
    }
}
