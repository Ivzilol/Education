package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static magicGame.common.ExceptionMessages.INVALID_MAGIC_REPOSITORY;

public class MagicRepositoryImpl implements MagicRepository<Magic>{

    private Collection<Magic> data;

    public MagicRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
        return Collections.unmodifiableCollection(this.data);
    }

    @Override
    public void addMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC_REPOSITORY);
        } else {
            this.data.add(magic);
        }
    }

    @Override
    public boolean removeMagic(Magic magic) {
        return this.data.remove(magic);
    }

    @Override
    public Magic findByName(String name) {
        return this.data.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }
}
