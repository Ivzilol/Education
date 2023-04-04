package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {


    private Collection<Computer> computers;

    private Collection<Component> components;

    private Collection<Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }


    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (computer != null) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        } else {
            switch (computerType) {
                case "Laptop":
                    computer = new Laptop(id, manufacturer, model, price);
                    break;
                case "DesktopComputer":
                    computer = new DesktopComputer(id, manufacturer, model, price);
                    break;
                default:
                    throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
            }
        }
        this.computers.add(computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        Component component = this.components.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (component != null) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        } else {
            switch (componentType) {
                case "CentralProcessingUnit":
                    component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                case "Motherboard":
                    component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                case "PowerSupply":
                    component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                case "RandomAccessMemory":
                    component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                case "SolidStateDrive":
                    component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                case "VideoCard":
                    component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                    break;
                default:
                    throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
            }
        }
        this.components.add(component);
        if (computer != null) {
            computer.addComponent(component);
        }
        return String.format(String.format(ADDED_COMPONENT, componentType, id, computerId));
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        Peripheral peripheral = this.peripherals.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (peripheral != null) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        } else {
            switch (peripheralType) {
                case "Headset":
                    peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Keyboard":
                    peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Monitor":
                    peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                case "Mouse":
                    peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                    break;
                default:
                    throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
            }
        }
        if (computer != null){
            computer.addPeripheral(peripheral);
        }
        this.peripherals.add(peripheral);
        return String.format(String.format(ADDED_PERIPHERAL, peripheralType, id, computerId));
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = this.computers.stream()
                .filter(c -> c.getId() == computerId).findFirst().orElse(null);
        String message = null;
        if (computer != null) {
            Peripheral peripheral = this.peripherals.stream().filter(p ->
                    p.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
            if (peripheral != null) {
                computer.removePeripheral(peripheralType);
                message = String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
            }
        }
        return message;
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = this.computers.stream()
                .filter(c -> c.getId() == computerId).findFirst().orElse(null);
        String message = null;
        if (computer != null) {
            Component component = this.components.stream().filter(c ->
                    c.getClass().getSimpleName().equals(componentType))
                    .findFirst().orElse(null);
            if (component != null) {
                computer.removeComponent(componentType);
                message = String.format(REMOVED_COMPONENT, componentType, component.getId());
            }
        }
        return message;
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (computer != null) {
            this.computers.remove(computer);
        }
        assert computer != null;
        return String.format(computer.toString());
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> underBudget = this.computers.stream().filter(c -> c.getPrice() <= budget).collect(Collectors.toList());
        if (!underBudget.isEmpty()) {
            List<Computer> bestComp = underBudget.stream().sorted((left, right) ->
                    (int) (right.getOverallPerformance() - left.getOverallPerformance()))
                    .limit(1).collect(Collectors.toList());
            Computer computerForBay = bestComp.get(0);
            this.computers.remove(computerForBay);
            return String.format(computerForBay.toString());
        } else {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (computer != null) {
            return String.format(computer.toString());
        } else {
            return null;
        }
    }
}
