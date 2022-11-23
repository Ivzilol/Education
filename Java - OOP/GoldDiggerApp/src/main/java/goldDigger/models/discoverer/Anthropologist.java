package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public class Anthropologist extends BaseDiscoverer {

    private static final double ANTHROPOLOGIST_ENERGY = 40;

    public Anthropologist(String name) {
        super(name, ANTHROPOLOGIST_ENERGY);
    }
}
