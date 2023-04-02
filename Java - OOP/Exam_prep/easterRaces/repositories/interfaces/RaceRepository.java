package easterRaces.repositories.interfaces;

import easterRaces.entities.racers.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository<T> implements Repository<Race>{

    private Collection<Race> races;

    public RaceRepository() {
        this.races = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return this.races.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.races);
    }

    @Override
    public void add(Race model) {
        this.races.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.remove(model);
    }
}
