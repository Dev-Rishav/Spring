package com.rishav.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dev {
    //field injection
//    @Autowired //this is called field injection
    //when autowiring is used, it seraches by type of the object in the container
    //searching by name is prohibited
//    private Laptop laptop;
    //without explicitly creating the object of laptop class, we can use application context but here we will use autowiring which will connect these two classes

    //constructor injection
//    public Dev(Laptop laptop){
//        this.laptop=laptop;
//    }

    //Setter injection
//    @Autowired
//    public void setLaptop(Laptop laptop){
//        this.laptop=laptop;
//    }
    @Autowired
    @Qualifier("desktop")
    private Computer comp;
    //instead of hardcoding the class laptop, we can dynamically use the computer type which is an interface
    //this is called loose coupling and autowiring searches by type which is here computer type


    // NOW HERE WE HAVE TWO CLASSES WITH SAME INTERFACE AND WUTOWIRE WILL BE CONFUSED BY THE TWO CLASSES
    //Either remove @component from one of the classes
    //or Add another field injection called @primary which will be put side by side with @component


    //instead of using @primary we can use @qualifier while creating the instance of the interface
    public void build(){
    comp.compile();
        System.out.println("Building the project...");
    }

}
