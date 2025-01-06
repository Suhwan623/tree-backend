package com.gbsw.tree.domain.auth.jwt.filter;

import com.gbsw.tree.domain.auth.jwt.provider.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException; // 정확한 SignatureException import
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String HEADER = "Authorization";
        String authorizationHeader = request.getHeader(HEADER);
        String token = getAccessToken(authorizationHeader);
        try {
            // 토큰이 있으면 인증 처리
            if (token != null) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 다음 필터로 이동
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            throw new JwtException("Invalid Token. 유효하지 않은 토큰");
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired Token. 토큰 기한 만료");
        } catch (SignatureException e) {
            throw new JwtException("Signature Failed. 인증 실패");
        }
    }

    // Access token을 추출하는 메서드
    private String getAccessToken(String authorizationHeader) {
        String PREFIX = "Bearer ";
        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX))
            return authorizationHeader.substring(PREFIX.length());
        else
            return null;
    }
}
