package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;

    private BeverageRepository<Beverages> beverageRepository;

    private TableRepository<Table> tableRepository;

    private double totalBill;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.beverageRepository = beverageRepository;
        this.healthFoodRepository = healthFoodRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood = healthFoodRepository.foodByName(name);
        if (healthyFood == null) {
            switch (type) {
                case "Salad":
                    healthyFood = new Salad(name, price);
                    break;
                case "VeganBiscuits":
                    healthyFood = new VeganBiscuits(name, price);
                    break;
            }
            this.healthFoodRepository.add(healthyFood);
            return String.format(FOOD_ADDED, name);
        } else {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages = this.beverageRepository.beverageByName(name, type);
        if (beverages == null) {
            switch (type) {
                case "Fresh":
                    beverages = new Fresh(name, counter, brand);
                    break;
                case "Smoothie":
                    beverages = new Smoothie(name, counter, brand);
                    break;
            }
            this.beverageRepository.add(beverages);
            return String.format(BEVERAGE_ADDED, type, brand);
        } else {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = this.tableRepository.byNumber(tableNumber);
        if (table == null) {
            switch (type) {
                case "Indoors":
                    table = new Indoors(tableNumber, capacity);
                    break;
                case "InGarden":
                    table = new InGarden(tableNumber, capacity);
                    break;
            }
        } else {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }
        this.tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table tableForReserve = tableRepository.getAllEntities().stream().filter(table ->
                        table.getSize() >= numberOfPeople && !table.isReservedTable())
                .findFirst().orElse(null);
        if (tableForReserve == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        } else {
            tableForReserve.reserve(numberOfPeople);
            return String.format(TABLE_RESERVED, tableForReserve.getTableNumber(), numberOfPeople);
        }
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);
        HealthyFood healthyFood = healthFoodRepository.foodByName(healthyFoodName);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        if (healthyFood == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(healthyFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);
        Beverages beverages = this.beverageRepository.beverageByName(name, brand);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        if (beverages == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }
        table.orderBeverages(beverages);
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double finalBill = table.bill();
        totalBill += finalBill;
        table.clear();
        return String.format(BILL, tableNumber, finalBill);

    }

    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, totalBill);
    }
}
