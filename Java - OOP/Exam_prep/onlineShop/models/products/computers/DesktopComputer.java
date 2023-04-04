package onlineShop.models.products.computers;

public class DesktopComputer extends BaseComputer{

    private static final double OVERALL_PERFORMANCE = 15;

    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, OVERALL_PERFORMANCE);
    }
}
