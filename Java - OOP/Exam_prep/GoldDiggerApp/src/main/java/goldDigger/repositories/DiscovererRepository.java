package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiscovererRepository implements Repository<Discoverer> {

    private Collection<Discoverer> data;

    public DiscovererRepository() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void add(Discoverer discoverer) {
        this.data.add(discoverer);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return this.data.remove(entity);
    }


    @Override
    public Discoverer byName(String name) {
        return this.data.stream()
                .filter(discoverer -> discoverer.getName().equals(name)).findFirst().orElse(null);
    }
}
