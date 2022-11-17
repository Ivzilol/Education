package garage;

public class Car {
    private String brand;
    private int hp;
    private int maxSpeed;
    private double price;

    public Car(String brand, int maxSpeed, double price) {
        this.setBrand(brand);
        this.setMaxSpeed(maxSpeed);
        this.setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
