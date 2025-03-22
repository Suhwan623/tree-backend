package com.gbsw.tree.domain.user.model;

import com.gbsw.tree.domain.admin.model.Asset;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Asset background;

    @ManyToOne
    private Asset tree;

    @ManyToOne
    private Asset treePoint;

}
