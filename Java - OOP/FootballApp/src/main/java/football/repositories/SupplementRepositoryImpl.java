package football.repositories;

import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepositoryImpl implements SupplementRepository{

    private Collection<Supplement> supplements;

    public SupplementRepositoryImpl() {
        this.supplements = new ArrayList<>();
    }

    @Override
    public void add(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public boolean remove(Supplement supplement) {
        if (this.supplements.contains(supplement)) {
            this.supplements.remove(supplement);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Supplement findByType(String type) {
        return this.supplements.stream()
                .filter(supplement -> supplement.getClass().getSimpleName().equals(type))
                .findFirst()
                .orElse(null);
    }
}
