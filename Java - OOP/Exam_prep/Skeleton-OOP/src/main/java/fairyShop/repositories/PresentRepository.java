package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PresentRepository<T> implements Repository<Present>{
    private List<Present> presents = new ArrayList<>();

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(this.presents);
    }

    @Override
    public void add(Present model) {
        this.presents.add(model);
    }

    @Override
    public boolean remove(Present model) {
        return this.presents.remove(model);
    }

    @Override
    public Present findByName(String name) {
        return this.presents.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}
