package com.likebookapp.service;

import com.likebookapp.model.entity.dto.AddPostDto;
import com.likebookapp.model.entity.dto.PostByUsers;
import com.likebookapp.model.entity.entity.Mood;
import com.likebookapp.model.entity.entity.Post;
import com.likebookapp.model.entity.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final MoodRepository moodRepository;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
    }

    public Set<Post> findCurrentUserPosts(Long id) {
        return this.postRepository.findPostFromCurrentUser(id);
    }

    public void addPost(AddPostDto addPostDto) {
        Post post = new Post();
        post.setContent(addPostDto.getContent());
        Mood mood = this.moodRepository.findMood(addPostDto.getMood());
        post.setMood(mood);
        Optional<User> userById = this.userRepository.findById(addPostDto.getId());
        post.setUser(userById.get());
        this.postRepository.save(post);
    }

    public void removePost(Long id) {
        this.postRepository.deleteById(id);
    }

    public Set<Post> getPostFromOtherUsers(Long id) {
        return this.postRepository.findPostOnOtherUsers(id);
    }

    public void likePostById(Long postId, Long userId) {
        Optional<Post> post = this.postRepository.findById(postId);
        Optional<User> user = this.userRepository.findById(userId);
        post.get().getUserLikes().add(user.get());
        post.get().setUserLikes(post.get().getUserLikes());
        postRepository.save(post.get());
    }
}
