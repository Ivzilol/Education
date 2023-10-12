package com.likebookapp.repository;

import com.likebookapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post as p" +
            " where p.user.id = :id")
    Set<Post> findPostCurrentUser(Long id);
    @Query("select p from Post as p" +
            " where p.user.id <> :id")
    Set<Post> findPostsOtherUsers(Long id);
}
