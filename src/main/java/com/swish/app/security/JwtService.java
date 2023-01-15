package com.swish.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET_KEY = "357638792F413F4428472B4B6250655368566D597133743677397A2443264529";

  public String extractUsername(final String token) {

    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {

    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(final UserDetails userDetails) {

    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      final Map<String, Object> extraClaims,
      final UserDetails userDetails
  ) {

    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .claim("roles", userDetails.getAuthorities()
            .toString())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(final String token, final UserDetails userDetails) {

    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(final String token) {

    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(final String token) {

    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(final String token) {

    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {

    final byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}