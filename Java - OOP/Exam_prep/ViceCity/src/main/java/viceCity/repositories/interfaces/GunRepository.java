package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository<T> implements Repository<Gun>{

    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayDeque<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Gun model) {
        if (!this.models.contains(model)) {
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return this.models.remove(model);
    }

    @Override
    public Gun find(String name) {
        return this.models.stream().filter(g -> g.getName().equals(name)).findFirst().orElse(null);
    }
}
