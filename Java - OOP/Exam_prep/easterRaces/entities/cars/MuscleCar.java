package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private static final double CUBIC_CENTIMETERS = 5000;

    private static final int MIN_HORSE_POWER = 400;
    private static final int MAX_HORSE_POWER = 600;

    public MuscleCar(String name, int horsePower) {
        super(name, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
    }
}
