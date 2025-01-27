package org.rishav;

public class Dev {
    private Computer comp;
    private int age;

//    while using getter, setter injection the naming should be same for the getter setter methods


    public Computer getComp() {
        return comp;
    }

    public void setComp(Computer comp) {
        this.comp = comp;
    }

    public Dev(){
        System.out.println("Dev object created!");
    }

    public void build(){
        System.out.println("Building the project...");
        comp.compile();
    }

}
