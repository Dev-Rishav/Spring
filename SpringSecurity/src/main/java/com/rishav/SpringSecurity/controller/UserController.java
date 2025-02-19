package com.rishav.SpringSecurity.controller;

import com.rishav.SpringSecurity.model.Users;
import com.rishav.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    @ResponseBody
    //!this annotation must be used to directly write onto http response body or otherwise we have to handle the response using views
    public Users register(@RequestBody Users user){
        return service.register(user);
    }
}
