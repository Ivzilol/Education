package com.example.exercises_07_json_procesing.productshop;

import com.example.exercises_07_json_procesing.productshop.entities.categories.CategoryStatsDTO;
import com.example.exercises_07_json_procesing.productshop.entities.categories.XMLCategoryStatsDTO;
import com.example.exercises_07_json_procesing.productshop.entities.products.ProductWithoutBayerDTO;
import com.example.exercises_07_json_procesing.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.exercises_07_json_procesing.productshop.services.ProductService;
import com.example.exercises_07_json_procesing.productshop.services.SeedService;
import com.example.exercises_07_json_procesing.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;

    private final Gson gson;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService,
                             ProductService productService,
                             UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();
        //productBetweenPriceWithoutBayer();
        //getUserWithSoldProducts();
        List<CategoryStatsDTO> result = getCategoryStats();
        String json = this.gson.toJson(result);
        System.out.println(json);

        XMLCategoryStatsDTO first = new XMLCategoryStatsDTO(result.get(0));

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(first, System.out);

    }

    private List<CategoryStatsDTO> getCategoryStats() {
        return this.productService.getCategoryStatistics();


    }

    private void getUserWithSoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();
        String json = this.gson.toJson(usersWithSoldProducts);
        System.out.println(json);
    }

    private void productBetweenPriceWithoutBayer() {
        List<ProductWithoutBayerDTO> productForSell =
                this.productService.getProductsInPriceRangeForSell((500), 1000);
        String json = this.gson.toJson(productForSell);
        System.out.println(json);
    }
}
