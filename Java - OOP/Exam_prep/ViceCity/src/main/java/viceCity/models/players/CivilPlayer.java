package viceCity.models.players;

public class CivilPlayer extends BasePlayer{

    private static final int LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, LIFE_POINTS);
    }
}
