package com.likebookapp.service;

import com.likebookapp.model.dto.CurrentUserDTO;
import com.likebookapp.model.dto.PostOtherUsersDTO;
import com.likebookapp.model.dto.PostsCurrentUserDTO;
import com.likebookapp.model.dto.UserRegistrationDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, PostRepository postRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }


    public Optional<User> findUserByUsername(String value) {
        return this.userRepository.findByUsername(value);
    }

    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        String encodePassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        Optional<User> newUser = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        return newUser.isPresent();
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }

    public void login(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            this.loggedUser.setId(user.get().getId());
            this.loggedUser.setUsername(user.get().getUsername());
        }
    }

    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public CurrentUserDTO findLoggedUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        CurrentUserDTO currentUserDTO = new CurrentUserDTO();
        currentUserDTO.setUsername(user.get().getUsername());
        return currentUserDTO;
    }

    public Set<PostsCurrentUserDTO> findPostCurrentUser(Long id) {
        Set<Post> allPosts = this.postRepository.findPostCurrentUser(id);
        Set<PostsCurrentUserDTO> postsCurrentUserDTO = new HashSet<>();
        for (Post post : allPosts) {
            int likes = 0;
            PostsCurrentUserDTO postsCurrentUserDTOs = new PostsCurrentUserDTO();
            postsCurrentUserDTOs.setContent(post.getContent());
            postsCurrentUserDTOs.setMood(post.getMood());
            postsCurrentUserDTOs.setId(post.getId());
            likes = post.getUserLikes().size();
            postsCurrentUserDTOs.setNumberOfLikes(likes);
            postsCurrentUserDTO.add(postsCurrentUserDTOs);
        }
        return postsCurrentUserDTO;
    }

    public Set<PostOtherUsersDTO> findPostOtherUsers(Long id) {
        Set<Post> allPosts = this.postRepository.findPostsOtherUsers(id);
        Set<PostOtherUsersDTO> postOtherUsersDTO = new HashSet<>();
        for (Post post : allPosts) {
            int likes = 0;
            PostOtherUsersDTO postOtherUsersDTOs = new PostOtherUsersDTO();
            postOtherUsersDTOs.setId(post.getId());
            Optional<User> user = this.userRepository.findById(post.getUser().getId());
            postOtherUsersDTOs.setUsername(user.get().getUsername());
            postOtherUsersDTOs.setContent(post.getContent());
            likes = post.getUserLikes().size();
            postOtherUsersDTOs.setNumberLikes(likes);
            postOtherUsersDTOs.setMood(post.getMood());
            postOtherUsersDTO.add(postOtherUsersDTOs);
        }
        return postOtherUsersDTO;
    }
}
