package com.gbsw.tree.domain.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenByRefreshToken {
    private String refreshToken;
}
