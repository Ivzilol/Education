package Exercises_06.SpeedRacing_03;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostForKilometer;
    private double distanceTraveled;

    public Car(String model, double fuelAmount, double fuelCostForKilometer, double distanceTraveled) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostForKilometer = fuelCostForKilometer;
        this.distanceTraveled = distanceTraveled;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getFuelCostForKilometer() {
        return fuelCostForKilometer;
    }

    public void setFuelCostForKilometer(double fuelCostForKilometer) {
        this.fuelCostForKilometer = fuelCostForKilometer;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    @Override
    public String toString() {
        String builder = model + " " + String.format("%.2f ", fuelAmount) +
                String.format("%.0f", distanceTraveled);
        return builder;
    }
}
