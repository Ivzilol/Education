package com.example.ShoppingList.service;

import com.example.ShoppingList.model.dto.*;
import com.example.ShoppingList.model.entity.Category;
import com.example.ShoppingList.model.entity.Product;
import com.example.ShoppingList.model.entity.User;
import com.example.ShoppingList.repository.CategoryRepository;
import com.example.ShoppingList.repository.ProductRepository;
import com.example.ShoppingList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Optional<Product> findByProductName(String value) {
        return this.productRepository.findByName(value);
    }

    public void addProduct(AddProductDTO addProductDTO, Long userId) {
        Product product = new Product();
        product.setName(addProductDTO.getName());
        product.setDescription(addProductDTO.getDescription());
        product.setNeededBefore(addProductDTO.getNeededBefore());
        product.setPrice(addProductDTO.getPrice());
        Category category = this.categoryRepository.findCategory(addProductDTO.getCategory());
        product.setCategory(category);
        Optional<User> user = this.userRepository.findById(userId);
        product.setUser(user.get());
        this.productRepository.save(product);
    }

    public Set<FoodDTO> getFood(Long id) {
        return this.productRepository.findProductFood(id);
    }

    public Set<DrinksDTO> getDrinks(Long id) {
        return this.productRepository.findProductDrinks(id);
    }

    public Set<HouseholdsDTO> getHouseholds(Long id) {
        return this.productRepository.findHouseholdDTO(id);
    }

    public Set<OthersDTO> findOthers(Long id) {
        return this.productRepository.findOthers(id);
    }

    public SumDTO getSum(Long id) {
        return this.productRepository.findSum(id);
    }

    public void boughtById(Long id, Long userId) {
        Optional<Product> product = this.productRepository.findById(id);
        this.productRepository.delete(product.get());
    }

    public void boughtAll(Long id) {
        Set<Product> products = this.productRepository.findAllProductCurrentUser(id);
        this.productRepository.deleteAll(products);
    }
}
