package football.entities.player;

import football.common.ExceptionMessages;

public abstract class BasePlayer implements Player {

    private String name;

    private String nationality;

    private double kg;

    private int strength;

    public BasePlayer(String name, String nationality, int strength) {
        this.name = name;
        this.nationality = nationality;
        this.strength = strength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            String exceptionMessage = ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY;
            throw new NullPointerException(exceptionMessage);
        } else {
            this.name = name;
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            String exceptionMessage = ExceptionMessages.PLAYER_NATIONALITY_NULL_OR_EMPTY;
            throw new NullPointerException(exceptionMessage);
        } else {
            this.nationality = nationality;
        }
    }

    @Override
    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength <= 0) {
            String exceptionMessage = ExceptionMessages.PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO;
            throw  new IllegalArgumentException(exceptionMessage);
        } else {
            this.strength = strength;
        }
    }

    @Override
    public void stimulation() {

    }
}
