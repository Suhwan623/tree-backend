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

    @Size(min = 2, max = 25)
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 8, max = 50)
    private String password;

    @ManyToOne
    private Asset background;

    @ManyToOne
    private Asset tree;

    @ManyToOne
    private Asset treePoint;

}
