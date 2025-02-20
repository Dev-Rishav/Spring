package com.rishav.SpringSecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {
    private String secretKey="";
    //hardcoding secretKey is not the proper way to handle things properly so we will create a constructor
    public JWTService(){
        try {
            KeyGenerator keyGenerator= KeyGenerator.getInstance("HmacSHA256");
            //initialised the object with the desired algorithm
            SecretKey sk = keyGenerator.generateKey();
            //key is generated which is of type SecretKey
            secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());
            //encode the SecretKey object to string and override the secretKey private var
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {

        Map<String, Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)    //token is added to the map object<String(username),object(all the attributes defined below)
                .subject(username)  //binds the username to the token
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60 * 60 *30))
                .and()  //and() adds the extra methods
                .signWith(getKey())     //this signs the token with the desired token
                .compact(); //compact will generate the token


    }

    private SecretKey getKey() {  //this method is just to return the secretKey after typecasting it

        byte[] keyBytes= Decoders.BASE64.decode(secretKey); //this converts the string into byte array of base 64
        //this converts it into the Key object with the desired algorithm but this accepts byte array
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //the extractUsername and validateToken methods are redundant as these are used once for every project

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName=extractUsername(token);
        return (userName.equals( (userDetails.getUsername()) ) && !isTokenExpired(token) );
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


}
