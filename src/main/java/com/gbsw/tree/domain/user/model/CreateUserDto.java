package com.gbsw.tree.domain.user.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String username;
    private String password;
    private String nickname;
}
