package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscovererRepository implements Repository<Discoverer> {

    private final Map<String, Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(this.discoverers.values());
    }

    @Override
    public void add(Discoverer discoverer) {
        discoverers.put(discoverer.getName(), discoverer);
    }

    @Override
    public boolean remove(Discoverer discoverer) {
        return discoverers.remove(discoverer.getName()) != null;
    }

    @Override
    public Discoverer byName(String name) {
        return discoverers.get(name);
    }
}
