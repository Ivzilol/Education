package onlineShop.models.products.components;

public class VideoCard extends BaseComponent{

    private static final double MULTIPLIER = 1.15;

    public VideoCard(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * MULTIPLIER, generation);
    }
}
