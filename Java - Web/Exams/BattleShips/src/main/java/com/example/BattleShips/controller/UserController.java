package com.example.BattleShips.controller;

import com.example.BattleShips.model.dto.UserLoginDTO;
import com.example.BattleShips.model.dto.UserRegistrationDTO;
import com.example.BattleShips.service.UserService;
import com.example.BattleShips.util.LoggedUser;
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

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @GetMapping("/register")
    public String register() {
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
}
