package goldDigger.models.discoverer;

public class Geologist  extends BaseDiscoverer{

    private static final double GEOLOGIST_ENERGY = 100;

    public Geologist(String name) {
        super(name, GEOLOGIST_ENERGY);
    }
}
