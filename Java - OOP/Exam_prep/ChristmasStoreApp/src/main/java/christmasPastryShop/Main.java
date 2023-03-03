package christmasPastryShop;

import christmasPastryShop.core.ControllerImpl;
import christmasPastryShop.core.EngineImpl;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.io.ConsoleReader;
import christmasPastryShop.io.ConsoleWriter;
import christmasPastryShop.repositories.interfaces.*;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String a = " ";
        int a1 = a.length();
        DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl(); // TODO: new DelicacyRepositoryImpl<>();
        CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl(); // TODO: new CocktailRepositoryImpl<>();
        BoothRepository<Booth> boothRepository = new BoothRepositoryImpl(); // TODO: new BoothRepositoryImpl<>();

        Controller controller = new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository); // TODO: new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository);

        // TODO:OPTIONAL

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
