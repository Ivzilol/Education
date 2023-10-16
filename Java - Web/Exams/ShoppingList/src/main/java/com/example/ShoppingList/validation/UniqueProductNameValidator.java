package com.example.ShoppingList.validation;


import com.example.ShoppingList.service.ProductService;
import com.example.ShoppingList.validation.annotation.UniqueProductName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

    private final ProductService productService;

    public UniqueProductNameValidator(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.productService.findByProductName(value).isEmpty();
    }
}
