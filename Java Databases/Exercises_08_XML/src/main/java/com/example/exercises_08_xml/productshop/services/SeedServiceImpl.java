package com.example.exercises_08_xml.productshop.services;


import com.example.exercises_08_xml.productshop.entities.categories.Category;
import com.example.exercises_08_xml.productshop.entities.categories.CategoryImportDTO;
import com.example.exercises_08_xml.productshop.entities.products.Product;
import com.example.exercises_08_xml.productshop.entities.products.ProductsImportDTO;
import com.example.exercises_08_xml.productshop.entities.users.User;
import com.example.exercises_08_xml.productshop.entities.users.UsersImportDTO;
import com.example.exercises_08_xml.productshop.repositories.CategoryRepository;
import com.example.exercises_08_xml.productshop.repositories.ProductRepository;
import com.example.exercises_08_xml.productshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_XML_PATH =
            Path.of("src","main","resources", "productshop","users.xml");
    private static final String CATEGORIES_XML_PATH = "src/main/resources/productshop/categories.xml";
    private static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "productshop", "products.xml");
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository
            ) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;


        this.mapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BufferedReader xmlReader = Files.newBufferedReader(USERS_XML_PATH);
        UsersImportDTO usersDTOs = (UsersImportDTO) unmarshaller.unmarshal(xmlReader);
        List<User> entities = usersDTOs
                .getUsers()
                .stream()
                .map(dto -> this.mapper.map(dto, User.class))
                .collect(Collectors.toList());
        this.userRepository.saveAll(entities);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH);
        CategoryImportDTO importDTO = (CategoryImportDTO) unmarshaller.unmarshal(xmlReader);
        List<Category> entities = importDTO
                .getCategories()
                .stream()
                .map(cn -> new Category(cn.getName()))
                .collect(Collectors.toList());
        this.categoryRepository.saveAll(entities);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        BufferedReader xmlReader = Files.newBufferedReader(PRODUCTS_XML_PATH);
        ProductsImportDTO importDTOs = (ProductsImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Product> entities = importDTOs
                .getProducts()
                .stream()
                .map(pr -> new Product(pr.getName(), pr.getPrice()))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .collect(Collectors.toList());
        this.productRepository.saveAll(entities);
    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < categoriesDbCount; i++) {
            int randomId = random.nextInt((int) categoriesDbCount) + 1;
            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }
        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0){
            return product;
        }
        Optional<User> bayer = getRandomUser();
        product.setBayer(bayer.get());

        return product;

    }

    private Product setRandomSeller (Product product){
        Optional<User> seller = getRandomUser();

        product.setSeller(seller.get());
        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1 ;

        return this.userRepository.findById(randomUserId);
    }
}
