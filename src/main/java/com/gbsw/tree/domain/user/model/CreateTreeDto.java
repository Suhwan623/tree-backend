package com.gbsw.tree.domain.user.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTreeDto {
    private Long background;
    private Long tree;
    private Long treePoint;
}
