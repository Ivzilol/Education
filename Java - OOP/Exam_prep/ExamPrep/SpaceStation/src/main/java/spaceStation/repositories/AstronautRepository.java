package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AstronautRepository<T> implements Repository<Astronaut>{

    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(this.astronauts);
    }

    @Override
    public void add(Astronaut astronaut) {
        this.astronauts.add(astronaut);
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        return this.astronauts.remove(astronaut);
    }

    @Override
    public Astronaut findByName(String astronaut) {
        return this.astronauts.stream().filter(a -> a.getName().equals(astronaut)).findFirst().orElse(null);
    }
}
