package heroRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HeroRepository {
    private Collection<Hero> data;

    public HeroRepository() {
        this.data = new ArrayList<>();
    }

    public int getCount() {
        return this.data.size();
    }

    public String create(Hero hero) {
        if (hero == null) {
            throw new NullPointerException("Hero is null");
        }

        if (this.data.stream().anyMatch(h -> h.getName().equals(hero.getName()))) {
            throw new IllegalArgumentException("Hero with name %s already exists");
        }

        this.data.add(hero);
        return String.format("Successfully added hero %s with level %d", hero.getName(), hero.getLevel());
    }

    public boolean remove(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("Name cannot be null");
        }

        boolean isRemoved = this.data.removeIf(h -> h.getName().equals(name));
        return isRemoved;
    }

    public Hero getHeroWithHighestLevel() {
        Hero hero = this.data
                .stream()
                .max(Comparator.comparingInt(Hero::getLevel))
                .orElse(null);

        return hero;
    }

    public Hero getHero(String name) {
        Hero hero = this.data
                .stream()
                .filter(h -> h.getName().equals(name))
                .findFirst()
                .orElse(null);

        return hero;
    }

    public Collection<Hero> getHeroes() {
        return Collections.unmodifiableCollection(this.data);
    }
}
