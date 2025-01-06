package com.gbsw.tree.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostResult {
    private String message;
    private Long sender;
    private Long receiver;
    private String imgName;
    private String senderName;
}
