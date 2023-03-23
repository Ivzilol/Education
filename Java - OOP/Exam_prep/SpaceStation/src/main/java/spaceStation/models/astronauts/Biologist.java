package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{

        private static final double INITIAL_OXYGEN = 70;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    public void breath() {
        setOxygen(getOxygen() - 5);
    }
}
