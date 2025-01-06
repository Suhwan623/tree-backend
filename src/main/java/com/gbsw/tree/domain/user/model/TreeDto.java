package com.gbsw.tree.domain.user.model;

import com.gbsw.tree.domain.post.model.PostDto;
import com.gbsw.tree.domain.post.model.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TreeDto {

    private List<String> treeInfo;

    private List<PostDto> posts;
}