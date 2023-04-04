package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {

    private int generation;

    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = String.format("Overall Performance: %s. Price: %.2f - %s: %s %s (Id: %d) Generation: %d",
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer()
                , getModel(), getId(), this.generation);
        sb.append(message);
        return sb.toString();
    }
}
