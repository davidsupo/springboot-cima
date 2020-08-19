package pe.edu.colegiocima.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtToken {
    // Constante de tiempo de validación del TOKEN
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    // Constante Clave secreta para generar el token
    public static final String JWT_SECRET = "cima_app_secret";

    // Generar el token
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }

    // Obtener todos los claims
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    //Función para obtener alguna propiedad especifica de los claims
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Obtener el username
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    // Obtener la fecha de expiración
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    // Validar si el token expiro
    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Validar token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

}
