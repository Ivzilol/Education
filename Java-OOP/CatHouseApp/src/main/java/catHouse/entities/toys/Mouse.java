package catHouse.entities.toys;

public class Mouse extends  BaseToy{

    private static final int MOUSE_SOFTNESS = 5;
    private static final int MOUSE_PRICE = 15;

    public Mouse() {
        super(MOUSE_SOFTNESS, MOUSE_PRICE);
    }
}
