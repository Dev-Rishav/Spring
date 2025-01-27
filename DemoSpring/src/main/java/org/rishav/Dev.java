package org.rishav;

public class Dev {
    private int age;
    public Dev(){
        System.out.println("Dev object created!");
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
