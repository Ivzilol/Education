package com.likebookapp.controller;

import com.likebookapp.model.dto.CurrentUserDTO;
import com.likebookapp.model.dto.PostOtherUsersDTO;
import com.likebookapp.model.dto.PostsCurrentUserDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping( "")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final PostService postService;

    public HomeController(LoggedUser loggedUser, UserService userService, PostService postService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("")
    public String home() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        CurrentUserDTO currentUserDTO = this.userService.findLoggedUser(loggedUser.getId());
        model.addAttribute("currentUserDTO", currentUserDTO);
        Set<PostsCurrentUserDTO> postsCurrentUserDTO = this.userService.findPostCurrentUser(loggedUser.getId());
        model.addAttribute("postsCurrentUserDTO",postsCurrentUserDTO);
        Set<PostOtherUsersDTO> postOtherUsersDTO = this.userService.findPostOtherUsers(loggedUser.getId());
        model.addAttribute("postOtherUsersDTO", postOtherUsersDTO);
        return "home";
    }

    @GetMapping("like-post/{id}")
    public String likePost(@PathVariable Long id) {
        this.postService.likePost(id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("remove/{id}")
    public String deletePost(@PathVariable Long id){
        this.postService.removePost(id);
        return "redirect:/home";
    }

}
