package com.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

//    @Value("${eCommerce.app.secret}")
    private String APP_SECRET="admin";

//    @Value("${eCommerce.expires.in}")
    private long EXPIRES_IN=120000000;

    public String generateJwtToken(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Date expireDate =   new Date(new Date().getTime() + EXPIRES_IN);
//        System.out.println(expireDate);
        return Jwts.builder().setSubject( userDetails.getId())
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    String getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
//        System.out.println("cgfj");
//        System.out.println(claims);
        return   claims.getSubject();
    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
//            System.out.println(!isTokenExpired(token));
            return !isTokenExpired(token);
        }catch (SignatureException e) {
        	
            return false;
        }catch (MalformedJwtException e) {
        	e.printStackTrace();
            return false;
        }catch (ExpiredJwtException e) {
        	e.printStackTrace();
            return false;
        }catch (UnsupportedJwtException e) {
        	e.printStackTrace();
            return false;
        }catch (IllegalArgumentException e) {
        	e.printStackTrace();
        	System.out.println("error");
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
//        System.out.println(expiration);
//        System.out.println(expiration.before(new Date()));
        return expiration.before(new Date());
    }

}
