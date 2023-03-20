package glacialExpedition.models.suitcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Carton implements Suitcase{

    List<String> exhibits;

    public Carton() {
        this.exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }
}
