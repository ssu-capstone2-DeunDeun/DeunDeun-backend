package kr.co.deundeun.groopy.config.security;

import io.jsonwebtoken.*;
import kr.co.deundeun.groopy.config.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final SecretKey SECRET_KEY;
    private final Long ACCESS_EXPIRES_IN;
    private final Long REFRESH_EXPIRES_IN;

    public JwtTokenProvider(AppProperties appProperties){
        this.SECRET_KEY = hmacShaKeyFor(appProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        this.ACCESS_EXPIRES_IN = appProperties.getAccessExpiresIn() * 1000;
        this.REFRESH_EXPIRES_IN = appProperties.getRefreshExpiresIn() * 1000;
    }

    public String createAccessToken(Authentication authentication){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_EXPIRES_IN);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                   .setSubject("ACCESS_TOKEN")
                   .setAudience(Long.toString(userPrincipal.getUser().getId()))
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SECRET_KEY)
                   .compact();
    }

    public String createRefreshToken(Authentication authentication){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_EXPIRES_IN);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                   .setSubject("REFRESH_TOKEN")
                   .setAudience(Long.toString(userPrincipal.getUser().getId()))
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SECRET_KEY)
                   .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken, String tokenType) {
        try {
            String sub = Jwts.parserBuilder()
                             .setSigningKey(SECRET_KEY)
                             .build()
                             .parseClaimsJws(authToken)
                             .getBody()
                             .getSubject();
            return tokenType.equals(sub);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
