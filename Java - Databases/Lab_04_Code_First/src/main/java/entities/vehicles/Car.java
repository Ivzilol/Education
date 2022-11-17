package entities.vehicles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "car")
public class Car extends Vehicle{

    private static final String CAR_TYPE = "Car";

    private int doorCount;

    public Car() {
    }

    public Car(int doorCount){
        super(CAR_TYPE, 2500);
        this.doorCount = doorCount;
    }

    public String getCarType(){
        return CAR_TYPE;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }
}
