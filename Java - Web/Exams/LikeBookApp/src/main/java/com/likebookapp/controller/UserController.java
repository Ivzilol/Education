package com.likebookapp.controller;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegistrationDTO;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }



    @GetMapping("/register")
    private String register() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (!Objects.equals(userRegistrationDTO.getPassword(), userRegistrationDTO.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same.")
            );
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDTO", userRegistrationDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/users/register";
        }

        boolean isRegister = this.userService.register(userRegistrationDTO);
        if (isRegister) {
            return "redirect:/users/login";
        } else {
            return "redirect:/users/register";
        }
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (this.loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
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
/*//        if (!this.loggedUser.isLogged()) {
//            return "redirect:/users/login";
//        }*/
        this.userService.logout();
        return "redirect:/";
    }
}
