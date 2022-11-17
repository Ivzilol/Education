package com.example.advquerying;

import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooRepository shampooRepository;

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public Runner(
            ShampooRepository shampooRepository,
            ShampooService shampooService,
            IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    public void run(String... args)  {




    }





    private void tasks (){
//        Scanner = new Scanner(System.in);
//        String first = scanner.nextLine();
//        String second = scanner.nextLine();
//
//        Set<String> names = Set.of(first, second);
//
//        this.shampooRepository.findByIngredientsNames(names)
//                .forEach(System.out::println);
//

//
//        String sizeName = scanner.nextLine();
//        Size size = Size.valueOf(sizeName);
//
//        this.shampooRepository.findBySizeOrderById(size)
//                .forEach(System.out::println);


//            LabTask
//            1
//        this.shampooService.selectBySize(Size.MEDIUM)
//                .forEach(System.out::println);
//            2
//        this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10)
//                .forEach(System.out::println);
//            3
//        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
//                .forEach(System.out::println);

//            4
//        this.ingredientService.selectNameStartWith("M")
//                .forEach(System.out::println);
//            5
//        this.ingredientService.selectInNames(
//                        List.of("Lavender",
//                                "Herbs",
//                                "Apple"
//                        ))
//                .forEach(System.out::println);

//            6
//        int count = this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.5));
//        System.out.println(count);

//            9
        // this.ingredientService.deleteByName("Nettle");

//            10
//        this.ingredientService.increasePriceByPercentage(0.1);


    }
}
