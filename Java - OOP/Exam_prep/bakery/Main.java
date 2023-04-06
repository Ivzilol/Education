package bakery;

import bakery.core.ControllerImpl;
import bakery.core.EngineImpl;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import bakery.io.ConsoleReader;
import bakery.io.ConsoleWriter;
import bakery.repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {

        String a = " ";
        int a1 = a.length();
        FoodRepository<BakedFood> foodRepository =  new FoodRepositoryImpl();
        DrinkRepository<Drink> drinkRepository = new DrinkRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();

        Controller controller = new ControllerImpl(foodRepository, drinkRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
