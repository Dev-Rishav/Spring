package com.rishav.myApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MyAppApplication.class, args);
		//this how we access methods of different class using object creation


//		Dev obj = new Dev();
		//instead of creating objects manually, we can take help of spring framework to create objects dynamically in the container
		ApplicationContext context=SpringApplication.run(MyAppApplication.class, args);
		//SpringApplication.run this line makes the container to store the objects of the classes
		//ApplicationContext is the class of which object is being created, the container is also an object of ApplicationContext type

		Dev obj=context.getBean(Dev.class);
		//this will not create an object instead it will fetch the already created object from the applicationContext container

		//now, the object of dev class have never been created before and  spring does not create object of all classes automatically
		//so we need to assing @component to every class that we want spring to handle the object creation	for.
		obj.build();

		//now the thing is we cant manually create object cause the number is huge and maintaining them is hectic
		// this is where spring came into ac
	}
}
