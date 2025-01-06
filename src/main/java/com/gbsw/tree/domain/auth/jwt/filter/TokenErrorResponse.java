package com.gbsw.tree.domain.auth.jwt.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenErrorResponse {
    private String message;
}
