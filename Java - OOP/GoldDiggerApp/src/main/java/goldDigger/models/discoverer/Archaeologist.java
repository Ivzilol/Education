package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public class Archaeologist extends BaseDiscoverer {

    private static final double ARCHAEOLOGIST_ENERGY = 60;
    private String name;

    public Archaeologist(String name) {
        super(name, ARCHAEOLOGIST_ENERGY);
    }
}
