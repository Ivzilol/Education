package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlanetRepository<T> implements Repository<Planet>{

    private final Collection<Planet> planets;

    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(this.planets);
    }

    @Override
    public void add(Planet planet) {
        this.planets.add(planet);
    }

    @Override
    public boolean remove(Planet planet) {
        return this.planets.remove(planet);
    }

    @Override
    public Planet findByName(String planet) {
        return this.planets.stream().filter(p -> p.getName().equals(planet)).findFirst().orElse(null);
    }
}
