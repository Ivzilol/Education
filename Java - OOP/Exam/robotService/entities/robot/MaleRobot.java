package robotService.entities.robot;

public class MaleRobot extends BaseRobot{

    private static final int INITIAL_KILOGRAMS = 9;

    public MaleRobot(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAMS, price);
    }

    @Override
    public void eating() {
        setKilograms(+3);
    }
}
