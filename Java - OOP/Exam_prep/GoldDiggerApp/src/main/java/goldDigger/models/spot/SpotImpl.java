package goldDigger.models.spot;

import goldDigger.models.museum.BaseMuseum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static goldDigger.common.ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY;

public class SpotImpl implements Spot {

    private String name;

    private Collection<String> exhibits;

    public SpotImpl(String name) {
        this.name = name;
        this.exhibits = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SPOT_NAME_NULL_OR_EMPTY);
        } else {
            this.name = name;
        }

    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
