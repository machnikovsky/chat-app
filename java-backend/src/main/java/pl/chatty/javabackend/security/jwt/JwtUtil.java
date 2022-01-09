package pl.chatty.javabackend.security.jwt;

import com.google.protobuf.Any;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String secret = "secret_to_be_changed_later";

    public String getUsernameFromToken(String token) {
        return (String) getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return (Date) getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = null;
        String username = userDetails.getUsername();
        return createToken(claims, username);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> Object getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())));
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())))
                .setExpiration(Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + 1000 * 60 * 60 * 10)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
