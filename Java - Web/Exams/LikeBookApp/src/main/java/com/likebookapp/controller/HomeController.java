package com.likebookapp.controller;

import com.likebookapp.model.entity.entity.Post;
import com.likebookapp.model.entity.entity.User;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(name = "/")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final PostService postService;

    public HomeController(LoggedUser loggedUser, UserService userService, PostService postService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping ("")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        Optional<User> user = userService.findCurrentUser(loggedUser.getId());
        model.addAttribute("currentUserInfo", user);
        Set<Post> postFromCurrentUser = this.postService.findCurrentUserPosts(loggedUser.getId());
        model.addAttribute("userPosts", postFromCurrentUser);
        Set<Post> postByUsers = this.postService.getPostFromOtherUsers(this.loggedUser.getId());
        model.addAttribute("otherUsersPost", postByUsers);
        return "home";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable Long id) {
        this.postService.removePost(id);
        return "redirect:/home";
    }

    @GetMapping("like-post/{id}")
    public String likePost(@PathVariable Long id) {
        postService.likePostById(id, loggedUser.getId());
        return "redirect:/home";
    }
}
