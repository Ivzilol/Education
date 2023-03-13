package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.areas.BaseArea;

import java.util.Collection;

public class WaterArea extends BaseArea {

    private static final int CAPACITY = 10;

    public WaterArea(String name) {
        super(name, CAPACITY);
    }
}
