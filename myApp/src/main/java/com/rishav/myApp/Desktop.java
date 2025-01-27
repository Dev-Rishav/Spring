package com.rishav.myApp;

import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")
public class Desktop implements Computer {
    public void compile(){
        System.out.println("Compiling from the custom class from desktop");
    }
    public void debug(){
        System.out.println("Debugging from desktop...");
    }
}
