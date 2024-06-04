package uz.pdp.store.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.login.SessionDto;
import uz.pdp.store.enitity.User;
import uz.pdp.store.unitls.CommonUtils;
import uz.pdp.store.unitls.MessageKey;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties properties;

    public SessionDto getSessionDto(User user) {
        return SessionDto.of(
                generateAccessToken(user),
                generateRefreshToken(user),
                getAccessTokenExpiry(),
                getRefreshTokenExpiry()
        );
    }

    public boolean isBearer(String token) {
        return !token.isBlank() && token.startsWith("Bearer");
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccessToken(User user) {
        return generateToken(user, properties.getAccessTokenExpiry());
    }

    public LocalDateTime getAccessTokenExpiry() {
        return getTokenExpiry(properties.getAccessTokenExpiry());
    }

    private String generateRefreshToken(User user) {
        return generateToken(user, properties.getRefreshTokenExpiry());
    }

    private String generateToken(User user, long expiryOnSeconds) {
        LocalDateTime now = CommonUtils.currentDateTime();
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(CommonUtils.fromLocalDateTimeToDate(now))
                .setExpiration(CommonUtils.fromLocalDateTimeToDate(now.plusSeconds(expiryOnSeconds)))
                .signWith(getSignInKey(properties.getSecretKey()), SignatureAlgorithm.HS256)
//                .claim("role", user.getRole().name())
                .compact();
    }

    private Key getSignInKey(String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private LocalDateTime getRefreshTokenExpiry() {
        return getTokenExpiry(properties.getRefreshTokenExpiry());
    }

    private LocalDateTime getTokenExpiry(long tokenExpiry) {
        return CommonUtils.currentDateTime().plusSeconds(tokenExpiry);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey(properties.getSecretKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException(MessageKey.TOKEN_EXPIRED);
        }
    }
}
