package com.rishav.SpringSecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students=new ArrayList<>(List.of(
            new Student(1,"Rishav",90),
            new Student(2,"Atul",80),
            new Student(3,"Vikas",70)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return  students;
    }

    //while using spring security for every post,put,delete operation we need CSRF token to do so.
    //everything in spring is converted into servlet and the request object have a lot of values one of  which is csrf token,
    //we can see that at the logout page inspection with the type="_csrf"
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    //one other way of doing this is, samesite strict where no  other site can access that
    @PostMapping("/students")
    public List<Student> addStudents(@RequestBody Student s){
        students.add(s);
        return students;
    }
}
