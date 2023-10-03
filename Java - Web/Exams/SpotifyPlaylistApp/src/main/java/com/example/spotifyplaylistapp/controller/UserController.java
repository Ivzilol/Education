package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.dto.UserLoginDTO;
import com.example.spotifyplaylistapp.model.entity.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    private String register() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (!Objects.equals(userRegisterDTO.getPassword(), userRegisterDTO.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same.")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }

        boolean isRegister = this.userService.registerUser(userRegisterDTO);
        if (isRegister) {
            return "redirect:/users/login";
        } else {
            return "redirect:/users/register";
        }
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @PostMapping("/login")
    public String loginUser( @Valid UserLoginDTO userLoginDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            return "redirect:/users/login";
        }

        boolean validateCredentials = this.userService.checkCredentials(
                userLoginDTO.getUsername(), userLoginDTO.getPassword()
        );

        if (!validateCredentials) {
            redirectAttributes
                    .addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/users/login";
        }

        this.userService.login(userLoginDTO.getUsername());
        return "redirect:/home";
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/logout")
    String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        this.userService.logout();
        return "redirect:/";
    }
}
