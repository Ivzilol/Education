package goldDigger.models.discoverer;

public class Anthropologist extends BaseDiscoverer{

    private static final double INITIAL_ENERGY = 40;

    public Anthropologist(String name) {
        super(name, INITIAL_ENERGY);
    }
}
