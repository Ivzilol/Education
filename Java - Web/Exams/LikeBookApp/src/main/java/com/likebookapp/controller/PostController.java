package com.likebookapp.controller;

import com.likebookapp.model.entity.dto.AddPostDto;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final LoggedUser loggedUser;

    private final PostService postService;

    public PostController(LoggedUser loggedUser, PostService postService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
    }

    @GetMapping("")
    public String posts() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "redirect:/home";
    }


    @GetMapping("/add-post")
    public String addPost(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "post-add";
    }

    @PostMapping("/add-post")
    public String addPost(@Valid AddPostDto addPostDto,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPostDto", addPostDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPostDto", result);
            return "redirect:/posts/add-post";
        }

        addPostDto.setId(loggedUser.getId());
        this.postService.addPost(addPostDto);
        return "redirect:/home";
    }

    @ModelAttribute
    public AddPostDto addPostDto() {
        return new AddPostDto();
    }
}