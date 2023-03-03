package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail>{

    Collection<Cocktail> cocktails;

    public CocktailRepositoryImpl() {
        this.cocktails = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return this.cocktails.stream()
                .filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(this.cocktails);
    }

    @Override
    public void add(Cocktail cocktail) {
        this.cocktails.add(cocktail);
    }
}
