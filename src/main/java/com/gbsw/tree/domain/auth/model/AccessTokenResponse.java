package com.gbsw.tree.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessTokenResponse {
    private String result;
    private String token;
    private String refreshToken;
}