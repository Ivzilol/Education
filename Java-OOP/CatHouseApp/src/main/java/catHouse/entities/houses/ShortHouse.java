package catHouse.entities.houses;

public class ShortHouse extends BaseHouse {

    private final static int SHORT_HOUSE_CAPACITY = 15;

    public ShortHouse(String name) {
        super(name, SHORT_HOUSE_CAPACITY);
    }
}
