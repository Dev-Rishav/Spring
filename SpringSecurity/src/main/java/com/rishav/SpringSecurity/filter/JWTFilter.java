package com.rishav.SpringSecurity.filter;


import com.rishav.SpringSecurity.service.JWTService;
import com.rishav.SpringSecurity.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
//to make it behave like a filter we have to inherit any filter class
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    //OncePerRequestFilter is used, because we only want to verify the token once for each session
    //OncePerRequestFilter is an abstract class so we need to define the methods which are abstract
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //now the request object will have the token
        //authentication -> bearer -> token
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSaXNoYXYiLCJpYXQiOjE3NDAwMjY3MjYsImV4cCI6MTc0MDAyNjgzNH0.cvhjF_Bp9-DeXnDaQPzeTthIBhJGq8sERGq986VYoWM

        String authHeader=request.getHeader("Authorization");   //returns the properties that is under authorization header
        String token=null;
        String username=null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7); //skip the "bearer "
            username=jwtService.extractUsername(token); //custom method to extract the username

        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //checks if the username is not null and the session is not already authenticated


            //here we will  validate the token and make it a success and navigate to next filter

            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            //this will create an object of the MyUserDetailsService class and its method will return the entire userDetails object defined in the MyUserDetailsService from the database


            if(jwtService.validateToken(token,userDetails)){
                //navigate to the next filter and pass the token
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                //this authToken object knows only about the userDetails attributes but we also need to bind the request object
                //details so we can do that by using WebAuthenticationDetailsSource().buildDetails() method.
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //now add this authToken to the chain
                SecurityContextHolder.getContext().setAuthentication(authToken);


            }

        }
        filterChain.doFilter(request,response); //this ensured the next filter is called from here
    }




}
