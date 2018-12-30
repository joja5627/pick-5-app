///*
// * Permission is hereby granted, free of charge, to any person obtaining a
// * copy of this software and associated documentation files (the "Software"),
// * to deal in the Software without restriction, including without limitation
// * the rights to use, copy, modify, merge, publish, distribute, sublicense,
// * and/or sell copies of the Software, and to permit persons to whom the
// * Software is furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included
// * in all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL
// * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//package io.pick5.token.service;
//
//import static io.jsonwebtoken.SignatureAlgorithm.HS256;
//import static io.jsonwebtoken.impl.TextCodec.BASE64;
//import static java.util.Objects.requireNonNull;
//import static lombok.AccessLevel.PRIVATE;
//import static org.apache.commons.lang3.StringUtils.substringBeforeLast;
//
//import java.time.Instant;
//import java.util.Date;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.google.common.base.Supplier;
//import com.google.common.collect.ImmutableMap;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.CompressionCodecs;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import lombok.experimental.FieldDefaults;
//
///**
// * A service to create JWT objects, this one is used when an exchange
// * provides basic authentication.
// * If authentication is successful, a token is added in the response
// */
//@Service
//@FieldDefaults(level = PRIVATE, makeFinal = true)
//public class JWTTokenService  implements  TokenService {
//	
//	  String issuer;
//	  String secretKey;
//	  String DOT = ".";
//	  
//	  int expirationSec;
//	  int clockSkewSec;
//	  
//	  Date currentInstant;
//	  
//	  Claims currentClaim;
//	  
//	  JWTTokenService(@Value("${jwt.issuer:octoperf}") final String issuer,
//              	@Value("${jwt.expiration-sec:86400}") final int expirationSec,
//              		@Value("${jwt.clock-skew-sec:300}") final int clockSkewSec,
//              			@Value("${jwt.secret:secret}") final String secret) {
//		super();
//		
//		this.issuer = requireNonNull(issuer);
//		this.expirationSec = requireNonNull(expirationSec);
//		this.clockSkewSec = requireNonNull(clockSkewSec);
//		this.secretKey = BASE64.encode(requireNonNull(secret));
//		currentInstant = Date.from(Instant.now());
//		currentClaim = Jwts.claims().setIssuer(issuer).setIssuedAt(currentInstant);
//	}
//	  private String newToken(final Map<String, String> attributes, final int expiresInSec) {
//
//		  currentClaim.putAll(attributes);
//		  return Jwts
//		    		.builder()
//		    			.setClaims(currentClaim)
//		    				.signWith(HS256, secretKey)
//		    					.compressWith(CompressionCodecs.DEFLATE)
//		    						.compact();
//		  }
//	 
//	@Override
//	public String permanent(Map<String, String> attributes) {
//		 return newToken(attributes, 0);
//	}
//
//	@Override
//	public String expiring(Map<String, String> attributes) {
//		return newToken(attributes, expirationSec);
//	}
//	@Override
//	  public Map<String, String> untrusted(final String token) {
//	    final JwtParser parser = Jwts
//	    							.parser()
//	    								.requireIssuer(issuer)
//	    									//TODO	      .setClock(this)
//	    									.setAllowedClockSkewSeconds(clockSkewSec);
//
//	    final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
//	    return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
//	  }
//
//	private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
//	    try {
//	      final Claims claims = toClaims.get();
//	      final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
//	      for (final Map.Entry<String, Object> e: claims.entrySet()) {
//	        builder.put(e.getKey(), String.valueOf(e.getValue()));
//	      }
//	      return builder.build();
//	    } catch (final IllegalArgumentException | JwtException e) {
//	      return ImmutableMap.of();
//	    }
//	  }
//	@Override
//	  public Map<String, String> verify(final String token) {
//	    final JwtParser parser = Jwts
//	      .parser()
//		      .requireIssuer(issuer)
//////TODO			      .setClock(this)
//		      .setAllowedClockSkewSeconds(clockSkewSec)
//		      .setSigningKey(secretKey);
//	    return parseClaims(() -> parser.parseClaimsJws(token).getBody());
//	  }
//}
