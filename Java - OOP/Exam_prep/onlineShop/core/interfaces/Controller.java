package onlineShop.core.interfaces;

public interface Controller {
    String addComputer(String computerType, int id, String manufacturer, String model, double price);

    String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType);

    String removePeripheral(String peripheralType, int computerId);

    String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation);

    String removeComponent(String componentType, int computerId);

    String buyComputer(int id);

    String BuyBestComputer(double budget);

    String getComputerData(int id);
}
