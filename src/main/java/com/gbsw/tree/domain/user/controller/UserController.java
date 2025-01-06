package com.gbsw.tree.domain.user.controller;

import com.gbsw.tree.domain.post.db.PostRepository;
import com.gbsw.tree.domain.post.model.MyPostDto;
import com.gbsw.tree.domain.post.model.PostDto;
import com.gbsw.tree.domain.user.model.*;
import com.gbsw.tree.domain.user.db.UserRepository;
import com.gbsw.tree.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResult> createUser(
            @RequestBody @Valid CreateUserDto dto, BindingResult result) {
        if(result.hasErrors()) {
            StringBuilder b = new StringBuilder();
            for(ObjectError error : result.getAllErrors()) {
                b.append(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new CreateUserResult(b.toString(), null));
        }

        User user = userService.createUser(dto);
        return ResponseEntity.ok(new CreateUserResult("ok", user.getUsername()));
    }

    @PatchMapping("/modify/{id}")
    public ResponseEntity<UpdateUserResult> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto dto,
            Principal principal) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!user.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }

        userService.UpdateUser(user, dto);

        return ResponseEntity.ok(new UpdateUserResult("ok", user.getEmail(), user.getNickname()));
    }

    @PostMapping("/tree")
    public ResponseEntity<CreateTreeResult> createTree(
        @Valid @RequestBody CreateTreeDto dto,
        Principal principal) {

       User user =  userService.getUser(principal.getName());

        userService.CreateTree(user, dto);

        return ResponseEntity.ok(new CreateTreeResult("ok", user.getId()));
    }

    @GetMapping("/tree/{id}")
    public ResponseEntity<?> getUserTree(@PathVariable Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자의 트리를 찾을 수 없습니다."));

        List<String> treeInfo = userService.getUserTreeById(user);


        List<PostDto> posts = postRepository.findAllByReceiver(user)
                .stream()
                .map(post -> new PostDto(post.getImgName(), post.getSenderName()))
                .toList();

        TreeDto response = new TreeDto();
        response.setTreeInfo(treeInfo);
        response.setPosts(posts);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tree/my")
    public ResponseEntity<?> getMyTree(Principal principal) {

        User user = userService.getUser(principal.getName());


        List<String> myTreeInfo = userService.getUserTreeById(user);


        List<MyPostDto> myPosts = postRepository.findAllByReceiver(user)
                .stream()
                .map(myPost -> new MyPostDto(myPost.getImgName(), myPost.getSenderName(), myPost.getContent()))
                .toList();
        MyTreeDto response  = new MyTreeDto();
        response.setTreeInfo(myTreeInfo);
        response.setPosts(myPosts);  // 편지 정보 설정

        return ResponseEntity.ok(response);
    }
}
