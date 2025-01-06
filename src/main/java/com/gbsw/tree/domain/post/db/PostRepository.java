package com.gbsw.tree.domain.post.db;

import com.gbsw.tree.domain.post.model.Post;
import com.gbsw.tree.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);

    List<Post> findAllByReceiver(User receiver);
}
