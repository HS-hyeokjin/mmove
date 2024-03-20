package com.move.move.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.security}")
    private String secretKey;
    private final long tokenValidTime = 1000L * 60 * 30;

    /**
     * secretKey 인코딩 후 자동 초기화 메서드
     */
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 토큰 생성 메서드
     * payLoad : userId, Signature : HS256, Expiration : 1시간
     */
    public String createToken(String userUid, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userUid);
        claims.put("roles",roles);
        Date now = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    /**
     * 사용자 인증 조회
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    /**
     * 토큰 디코딩 메서드
     * userName 추출
     */
    public String getUsername(String token){
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        return info;
    }

    /**
     * 쿠키에서 토큰값 추출 메서드
     */
    public String resolveToken(HttpServletRequest request){
        String tokenValue = request.getHeader("Authorization");
        if (tokenValue == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    String name = c.getName();
                    String value = c.getValue();
                    if (name.equals("Authorization")) {
                        tokenValue = value;
                    }
                }
            }
        }
        return tokenValue;
    }

    /**
     * 토큰 유효기간 확인
     * 유효기간 지났을 시 false
     */
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
