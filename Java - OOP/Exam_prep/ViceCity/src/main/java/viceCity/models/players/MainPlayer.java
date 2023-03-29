package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    private static final int LIFE_POINTS = 100;

    public MainPlayer(String name) {
        super(name, LIFE_POINTS);
    }
}
