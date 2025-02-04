package com.rishav.SpringSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //this specifies spring that this is a configuration file
@EnableWebSecurity //this specifies that dont go with the default security configuration, follow this
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return  http.build();
//    }
    //upto this point we have specified the custom filter chain but have not added any filter, so at this point the login and logout filters wont be there on the API

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //disabling the CSRF token auth using lambda function (the main goal was to go stateless)
//        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//
//        //enabling the form login operation
////        http.formLogin(Customizer.withDefaults()); //commented out because of stateless session
//        //now enabling auth for rest API (postman)
//        http.httpBasic(Customizer.withDefaults());
//        //going stateless
//        http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //now this will reset every time authentication works so an endless loop will occur on the browser end but not on the postman
        //cause in browser a form is being passed to collect the creds and then the authenticated state is applied so after successful authentication
        // the state is changed, to stop this from happening in the browser end we need to comment out the form option

        //now lets do this using native method, without lambda
        //http.csrf needs a CsrfConfigurer<HttpSecurity> object
        //CsrfConfigurer<HttpSecurity> is a functional interface so lamda can be implemented
//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf=new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//
//        http.csrf(custCsrf);
        //so basically same thing will be followed for every lamda expressions
//        return http.build();
        //using builder pattern to apply different operations on an object
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

}
