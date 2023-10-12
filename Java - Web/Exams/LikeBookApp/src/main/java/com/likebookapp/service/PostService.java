package com.likebookapp.service;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final MoodRepository moodRepository;


    public PostService(PostRepository postRepository, UserRepository userRepository, MoodRepository moodRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
    }

    public void createPost(AddPostDTO addPostDTO, Long userId) {
        Post post = new Post();
        post.setContent(addPostDTO.getContent());
        Optional<User> user = userRepository.findById(userId);
        post.setUser(user.get());
        Mood mood = this.moodRepository.findMood(addPostDTO.getMood());
        post.setMood(mood);
        this.postRepository.save(post);
    }

    public void likePost(Long id, Long loggedUserId) {
        Optional<Post> post = this.postRepository.findById(id);
        Optional<User> user = this.userRepository.findUserById(loggedUserId);
        post.get().getUserLikes().add(user.get());
        this.postRepository.save(post.get());
    }

    public void removePost(Long id) {
        this.postRepository.deleteById(id);
    }
}
