package com.pragma.hogar360.serviceshome.infrastructure.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String extractUsername(String token) {
        System.out.println("JwtService (home) - Token recibido para extraer username: " + token);
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("JwtService (home) - Extracting claim from token: " + token);
        final Claims claims = extractAllClaims(token);
        System.out.println("JwtService (home) - Claim extracted: " + claims);
        return claimsResolver.apply(claims);
    }

    public List<SimpleGrantedAuthority> extractRoles(String token) {
        System.out.println("JwtService (home) - Extracting roles from token: " + token);
        List<String> rolesFromToken = (List<String>) extractClaim(token, claims -> claims.get("roles"));

        System.out.println("JwtService (home) - Roles extraídas del token (raw): " + rolesFromToken);

        List<SimpleGrantedAuthority> authorities = null;
        if (rolesFromToken != null) {
            authorities = rolesFromToken.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            System.out.println("JwtService (home) - Authorities creadas: " + authorities);
        } else {
            System.out.println("JwtService (home) - No se encontraron roles en el token.");
        }
        return authorities;
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }

    private java.util.Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        System.out.println("JwtService (home) - Parsing token: " + token);
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}