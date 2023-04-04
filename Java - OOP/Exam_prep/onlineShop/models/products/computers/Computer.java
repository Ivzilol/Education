package onlineShop.models.products.computers;

import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.List;

public interface Computer extends Product {
    List<Component> getComponents();

    List<Peripheral> getPeripherals();

    void addComponent(Component component);

    Component removeComponent(String componentType);

    void addPeripheral(Peripheral peripheral);

    Peripheral removePeripheral(String peripheralType);
}