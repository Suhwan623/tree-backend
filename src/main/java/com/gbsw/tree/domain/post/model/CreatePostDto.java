package com.gbsw.tree.domain.post.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostDto {
    private String content;
    private String imgName;
    private String senderName;
}
