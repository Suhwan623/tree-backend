package com.gbsw.tree.domain.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserResult {
    private String message;
    private String nickname;
    private String id;

    // Long 타입의 id를 받아 String으로 변환하는 생성자
    public GetUserResult(String message, String nickname, Long id) {
        this.message = message;
        this.nickname = nickname;
        this.id = id != null ? id.toString() : null;
    }
}