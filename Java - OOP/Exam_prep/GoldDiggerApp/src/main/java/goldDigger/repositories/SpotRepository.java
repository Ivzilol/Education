package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SpotRepository implements Repository<Spot>{

    private Collection<Spot> spots;

    public SpotRepository() {
        this.spots = new ArrayList<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(this.spots);
    }

    @Override
    public void add(Spot spot) {
        this.spots.add(spot);
    }

    @Override
    public boolean remove(Spot spot) {
        return this.spots.remove(spot);
    }

    @Override
    public Spot byName(String name) {
        return this.spots.stream()
                .filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }
}
