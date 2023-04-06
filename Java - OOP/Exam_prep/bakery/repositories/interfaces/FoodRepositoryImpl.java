package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood>{

    private Collection<BakedFood> foods;

    public FoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.foods.stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(this.foods);
    }

    @Override
    public void add(BakedFood bakedFood) {
        this.foods.add(bakedFood);
    }
}
