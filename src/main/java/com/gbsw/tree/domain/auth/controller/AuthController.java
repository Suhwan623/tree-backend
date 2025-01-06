package com.gbsw.tree.domain.auth.controller;

import com.gbsw.tree.domain.auth.jwt.service.TokenService;
import com.gbsw.tree.domain.auth.model.AccessTokenRequest;
import com.gbsw.tree.domain.auth.model.AccessTokenResponse;
import com.gbsw.tree.domain.auth.model.CreateAccessTokenByRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(
            @RequestBody AccessTokenRequest request
    ){
        AccessTokenResponse token = tokenService.getAccessToken(request);
        if(token != null) {
            return ResponseEntity.ok().body(token);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login/token")
    public ResponseEntity<AccessTokenResponse> tokenLogin(
            @RequestBody CreateAccessTokenByRefreshToken request
    ){
        AccessTokenResponse response = tokenService.refreshAccessToken(request);
        if(response != null)
            return ResponseEntity.ok().body(response);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
