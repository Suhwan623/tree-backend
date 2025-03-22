package com.gbsw.tree.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String username;
    private String nickname;
    private String password;
}
