package goldDigger.models.discoverer;

public class Geologist extends BaseDiscoverer{

    private static final double INITIAL_ENERGY = 100;

    public Geologist(String name) {
        super(name, INITIAL_ENERGY);
    }
}
