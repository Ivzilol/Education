package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car{

    private String model;

    private int horsePower;

    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    public void setModel(String model) {
        if (model == null || model.trim().length() < 4) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, 4));
        }
        this.model = model;
    }

    protected abstract void checkHorsePower(int horsePower);

    public void setHorsePower(int horsePower) {
        this.checkHorsePower(horsePower);
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        double racePoints = 0;
       racePoints = cubicCentimeters / horsePower * laps;
       return racePoints;
    }
}
