package com.likebookapp.repository;

import com.likebookapp.model.entity.dto.PostByUsers;
import com.likebookapp.model.entity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PostRepository  extends JpaRepository<Post, Long>{
    @Query("select p from Post as p" +
            " where p.user.id = :id")
    Set<Post> findPostFromCurrentUser(Long id);

    @Query("select p from Post as p" +
            " where p.user.id <> :id")
    Set<Post> findPostOnOtherUsers(Long id);
}
