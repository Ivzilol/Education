package goldDigger.models.spot;

import goldDigger.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public class SpotImpl implements Spot {

    private  String name;
    private  Collection<String> exhibits;

    public SpotImpl(String name) {
        this.name = name;
        this.exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name == null || this.name.trim().isEmpty()) {
            String exceptionMessage = ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY;
            throw new NullPointerException(exceptionMessage);
        }
        this.name = name;
    }
}
