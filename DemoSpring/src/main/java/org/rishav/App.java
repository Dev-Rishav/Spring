package org.rishav;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{

    public static void main( String[] args )
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
//        Dev obj=(Dev)context.getBean("dev");
        //instaed of usnig Dev.class we can directly pass the class id which is defined on
        //the spring xml, but we need to explicitly type cast the object into the required class.
//        obj.build();
//        System.out.println( "Hello World!" );
    }
}
