package restaurant.entities.tables;

import restaurant.entities.drinks.BaseBeverage;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFoods;

    private Collection<Beverages> beverages;

    private int number;

    private int size;

    private int numberOfPeople;

    private double pricePerPerson;

    private boolean isReservedTable;

    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFoods = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.allPeople = 0;
        this.isReservedTable = false;
    }

    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    public void setAllPeople(double allPeople) {
        this.allPeople = numberOfPeople * pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        isReservedTable = true;
        this.allPeople = pricePerPerson * numberOfPeople;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFoods.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double bill = 0;
        for (Beverages beverages1 : this.beverages) {
            bill += beverages1.getPrice();
        }
        for (HealthyFood healthyFood : this.healthyFoods) {
            bill += healthyFood.getPrice();
        }
        bill += allPeople;
        return bill;
    }

    @Override
    public void clear() {
        this.beverages.clear();
        this.healthyFoods.clear();
        isReservedTable = false;
        this.numberOfPeople = 0;
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {
        String sb = String.format("Table - %d", number) +
                System.lineSeparator() +
                String.format("Size - %d", size) +
                System.lineSeparator() +
                String.format("All price - %.2f", allPeople) +
                System.lineSeparator();
        return sb.trim();
    }
}
