package io.pick5.token.service;


import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.experimental.FieldDefaults;
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JWTAuthenticationProvider implements AuthenticationProvider {

    public static final long TOKEN_DURATION_SECONDS = 60 * 60 * 24 * 7; // 1 week
    public static final long TOKEN_CREATION_BUFFER_SECONDS = 60 * 5; // 5 min
    
    public static final String ISSUER_ID = "pick-5-token-service";

    protected String secret;

    public JWTAuthenticationProvider(String tSecret) {
        secret = tSecret;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthentication jwtAuth = (JWTAuthentication)authentication;
        Jws<Claims> jws;
        try {
            jws = Jwts
            		.parser()
                    	.requireIssuer(ISSUER_ID)
                    		.setSigningKey(secret)
                    			.parseClaimsJws(jwtAuth.getToken());
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                IllegalArgumentException ex) {
            throw new BadCredentialsException("The token is not valid");
        }
        Date checkDate = Date.from(Instant.now());
        Date expirDate = jws.getBody().getExpiration();
        if (null == expirDate || checkDate.after(expirDate) ) {
            throw new BadCredentialsException("The token is expired");
        }
        Date notBeforeDate = jws.getBody().getNotBefore();
        if (null == notBeforeDate || checkDate.before(notBeforeDate) ) {
            throw new BadCredentialsException("The token not before date is invalid");
        }
        jwtAuth.setTokenClaims(jws.getBody());
        jwtAuth.setAuthenticated(true);
        return jwtAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }

    public String createJWTToken(String username) {
         return Jwts.builder()
                 .setSubject(username)
                 .setIssuer(ISSUER_ID)
                 .setIssuedAt(Date.from(Instant.now()))
                 .setExpiration(Date.from(Instant.now().plusSeconds(TOKEN_DURATION_SECONDS)))
                 .setNotBefore(Date.from(Instant.now().minusSeconds(TOKEN_CREATION_BUFFER_SECONDS)))
                 .signWith(SignatureAlgorithm.HS512, secret)
                 .compact();
    }
}
