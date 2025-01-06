package com.gbsw.tree.domain.post.service;

import com.gbsw.tree.domain.post.db.PostRepository;
import com.gbsw.tree.domain.post.model.CreatePostDto;
import com.gbsw.tree.domain.post.model.Post;
import com.gbsw.tree.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Post createPost(CreatePostDto dto, User user, User receiver) {

        Post post = Post.builder()
                .content(dto.getContent())
                .imgName(dto.getImgName())
                .senderName(dto.getSenderName())
                .user(user)
                .receiver(receiver)  // 받는 사람 설정
                .build();

        return postRepository.save(post);
    }
}
