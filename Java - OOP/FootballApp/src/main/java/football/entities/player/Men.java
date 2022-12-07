package football.entities.player;

public class Men extends BasePlayer {

    private static final double KG_MEN = 85.50;

    private static  final int INCREASE_STRENGTH_MEN = 145;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(KG_MEN);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASE_STRENGTH_MEN);
    }
}
