package org.rishav;

public class Dev {
    private Laptop laptop;
    private int age;

    public Laptop getLaptop(){
        System.out.println("printing laptop form getter method");
        return laptop;
    }
    public void setLaptop(Laptop laptop){
        this.laptop=laptop;
    }
//    while using getter, setter injection the naming should be same for the getter setter methods


    public Dev(){
        System.out.println("Dev object created!");
    }

    public Dev(int age){
        this.age=age;
        System.out.println("Using parameterized constructor");
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void build(){
        System.out.println("Building the project...");
    }

}
