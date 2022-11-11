package com.example.errors.web;

import com.example.errors.model.CategoryDTO;
import com.example.errors.model.ObjectNotFoundException;
import com.example.errors.model.ProductDTO;
import com.example.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("category")
@Controller
public class CategoryController {

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable("id") Long id,
                                 Model model) {
        CategoryDTO categoryDTO = getCategoryDTOById(id);

        if (categoryDTO == null){
            throw new ObjectNotFoundException(id);
        }

        model.addAttribute("name", categoryDTO.getCategoryName());
        return "category";
    }

    private CategoryDTO getCategoryDTOById(Long id) {
        return null;
    }
}
