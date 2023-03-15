package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument{

    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        } else {
            this.power = power;
        }
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        power -= 10;
        if (power < 0) {
            setPower(0);
        }
    }

    @Override
    public boolean isBroken() {
        return power <= 0;
    }

}
