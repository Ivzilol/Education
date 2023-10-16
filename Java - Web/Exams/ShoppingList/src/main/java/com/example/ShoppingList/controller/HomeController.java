package com.example.ShoppingList.controller;

import com.example.ShoppingList.model.dto.*;
import com.example.ShoppingList.service.ProductService;
import com.example.ShoppingList.service.UserService;
import com.example.ShoppingList.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final ProductService productService;


    public HomeController(LoggedUser loggedUser, UserService userService, ProductService productService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.productService = productService;
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
        Set<FoodDTO> foodDTO = this.productService.getFood(loggedUser.getId());
        model.addAttribute("foodDTO", foodDTO);
        Set<DrinksDTO> drinksDTO = this.productService.getDrinks(loggedUser.getId());
        model.addAttribute("drinksDTO", drinksDTO);
        Set<HouseholdsDTO> householdsDTO = this.productService.getHouseholds(loggedUser.getId());
        model.addAttribute("householdsDTO", householdsDTO);
        Set<OthersDTO> othersDTO = this.productService.findOthers(loggedUser.getId());
        model.addAttribute("othersDTO", othersDTO);
        SumDTO sumDTO = this.productService.getSum(loggedUser.getId());
        model.addAttribute("sumDTO", sumDTO);
        return "home";
    }

    @GetMapping("/bought-product/{id}")
    public String boughtProduct(@PathVariable Long id) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        this.productService.boughtById(id, loggedUser.getId());
        return "redirect:/home";
    }

    @GetMapping("/bought-all")
    public String boughtAll() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        this.productService.boughtAll(loggedUser.getId());
        return "redirect:/home";
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
