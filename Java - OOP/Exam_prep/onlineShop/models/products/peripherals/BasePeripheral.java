package onlineShop.models.products.peripherals;

import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {

    private String connectionType;

    public BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = String.format("Overall Performance: %s. Price: %.2f - %s: %s %s (Id: %d) Connection Type: %s",
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer()
                , getModel(), getId(), this.connectionType);
        sb.append(message);
        return sb.toString();
    }
}
