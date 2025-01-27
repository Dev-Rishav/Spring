package com.rishav.myApp;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
    public void compile(){
        System.out.println("Compiling from the custom class");
    }
    public void debug(){
        System.out.println("Debugging...");
    }
}
