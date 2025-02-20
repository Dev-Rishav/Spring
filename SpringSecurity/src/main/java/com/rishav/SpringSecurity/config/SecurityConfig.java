package com.rishav.SpringSecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //this specifies spring that this is a configuration file
@EnableWebSecurity //this specifies that dont go with the default security configuration, follow this
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
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
        //using builder patter=zn to apply different operations on an object
        return http
                .csrf(customizer -> customizer.disable())
                //the requestMatchers("").permitAll() method keep those routes free from authentication so users can login and register.
                .authorizeHttpRequests(request-> request
                        .requestMatchers("register","login").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }


    //this is for hardcoding stuff but we basically handle the users into the database dynamically
//    @Bean
//    public UserDetailsService userDetailsService (){
//        //as we can see that userDetailsService is an interface. so we have to either implement it or use an InMemoryUserDetailsManager()
//        //now this InMemoryUserDetailsManager() returns an empty object of the existing users in the DB
//        //so we need to assign users into the database
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("baji")
//                .password("1234")
//                .roles("USER")
//                .build();
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("atul")
//                .password("1234")
//                .roles("USER")
//                .build();
//        //this will create an user with encoded entries. Dont use withDefaultPasswordEncoder() in production
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //this will only fetch the user object from the DB
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        //previously using no password encoder but now using bcrypt
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    //to use custom authentication techniques like JWT we need to handle our own authenticationManager
    //authentication manager automatically calls the authenticationProvider object
    //whenever we use custom function, we use bean annotation

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        //authenticationManager is an interface so we need to use the AuthenticationConfiguration object
        //getAuthenticationManger() returns the object
        return config.getAuthenticationManager();
    }

}
