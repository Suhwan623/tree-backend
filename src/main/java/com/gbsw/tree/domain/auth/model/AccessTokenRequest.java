package com.gbsw.tree.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessTokenRequest {
    String username;
    String password;
}
