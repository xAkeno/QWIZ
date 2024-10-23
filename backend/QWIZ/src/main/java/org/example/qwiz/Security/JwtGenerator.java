package org.example.qwiz.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtGenerator {
    private final static String SECRET_KEY = "TSSOiGuGV+hVO/K1VZCsDgpc40aEjiD70BKjk4CzYY3j4nt+w220jQGgHAaUpyEl";
    public static boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static String extractUsername(String token) {
        try{
            Claims claims = Jwts.
                    parser().
                    setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }catch (Exception e){
            return null;
        }
    }
}
