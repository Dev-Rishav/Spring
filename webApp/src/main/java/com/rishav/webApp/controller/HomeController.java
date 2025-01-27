package com.rishav.webApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
//    @RequestMapping("/")
//    public String greet(){
//        return "Hello World!";
//    }
    //in good old days spring used to return a view name, but now it returns the string itself
//    we can achieve the same by annoting @responsebody at the method or @RestController
//    at the controller class

    @RequestMapping("/")
    public String greet(){
        return "Hello World!";
    }
}
