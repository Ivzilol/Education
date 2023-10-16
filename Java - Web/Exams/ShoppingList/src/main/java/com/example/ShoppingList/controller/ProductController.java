package com.example.ShoppingList.controller;

import com.example.ShoppingList.model.dto.AddProductDTO;
import com.example.ShoppingList.service.ProductService;
import com.example.ShoppingList.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final LoggedUser loggedUser;
    private final ProductService productService;

    public ProductController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping("/add-product")
    public String addProduct() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/add-product")
    public String userAddProduct(@Valid AddProductDTO addProductDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addProductDTO", addProductDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", result);
            return "redirect:/products/add-product";
        }
        addProductDTO.setId(loggedUser.getId());
        this.productService.addProduct(addProductDTO, loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public AddProductDTO addProductDTO() {
        return new AddProductDTO();
    }
}
