package com.gbsw.tree.domain.post.controller;

import com.gbsw.tree.domain.post.model.CreatePostDto;
import com.gbsw.tree.domain.post.model.CreatePostResult;
import com.gbsw.tree.domain.post.model.Post;
import com.gbsw.tree.domain.post.service.PostService;
import com.gbsw.tree.domain.user.db.UserRepository;
import com.gbsw.tree.domain.user.model.User;
import com.gbsw.tree.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/{id}")
    public ResponseEntity<CreatePostResult> createPost(
            @PathVariable Long id,
            @RequestBody @Valid CreatePostDto dto,
            Principal principal) {

        User receiver = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));

        User user = userService.getUser(principal.getName());

        Post post = postService.createPost(dto, user, receiver);

        return ResponseEntity.ok(new CreatePostResult("ok", user.getId(), receiver.getId(), post.getImgName(), post.getSenderName()
        ));
    }
}

