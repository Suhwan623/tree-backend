package com.gbsw.tree.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPostDto {
    private Long id;
    private String imgName;
    private String nickname;
    private String content;
}

