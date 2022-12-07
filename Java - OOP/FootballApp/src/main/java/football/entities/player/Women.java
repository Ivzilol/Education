package football.entities.player;

public class Women extends BasePlayer {

    private static final double KG_WOMEN = 60.00;
    private static  final int INCREASE_STRENGTH_WOMEN = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(KG_WOMEN);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASE_STRENGTH_WOMEN);
    }
}
