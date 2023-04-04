package onlineShop.models.products.computers;

import com.sun.source.tree.UsesTree;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;

    private List<Peripheral> peripherals;


    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public double getPrice() {
        double componentPrice = this.components.stream().mapToDouble(Component::getPrice).sum();
        double peripheralPrice = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        return super.getPrice() + componentPrice + peripheralPrice;
    }

    @Override
    public double getOverallPerformance() {
        return super.getOverallPerformance();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (this.components.contains(component)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    component.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), component.getId()));
        } else {
            this.components.add(component);
        }
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = this.components.stream().filter(c ->
                c.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        if (this.components.isEmpty() || component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    this.getClass().getSimpleName(), this.getClass().getSimpleName(),
                    this.getId()));
        } else {
            this.components.remove(component);
            return component;
        }
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (this.peripherals.contains(peripheral)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), peripheral.getId()));
        } else {
            this.peripherals.add(peripheral);
        }
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals.stream().filter(p ->
                p.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
        if (this.peripherals.isEmpty() || peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    this.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        } else {
            this.peripherals.remove(peripheral);
            return peripheral;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = String.format("Overall Performance: %s. Price: %.2f - %s: %s %s (Id: %d)",
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer(),
                getModel(), getId());
        sb.append(message);
        sb.append(System.lineSeparator());
        sb.append(String.format("Components(%d):", this.components.size()));
        sb.append(System.lineSeparator());
        for (Component component : this.components) {
            sb.append(component.toString());
            sb.append(System.lineSeparator());
        }
        if (this.peripherals.isEmpty()) {
            sb.append(String.format("Peripherals (%d); ", 0));
            sb.append(sb.append(String.format("Average Overall Performance(%.2f):", this.getOverallPerformance())));
            sb.append(System.lineSeparator());
        } else {
            double sumPerformance = this.peripherals.stream().mapToDouble(Product::getOverallPerformance).sum();
            double averagePerformance = (sumPerformance + this.getOverallPerformance()) / this.peripherals.size();
            sb.append(String.format("Peripherals (%d); ", this.peripherals.size()));
            sb.append(String.format("Average Overall Performance(%.2f):", averagePerformance));
            sb.append(System.lineSeparator());
            for (Peripheral peripheral : this.peripherals) {
                sb.append(peripheral.toString());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
