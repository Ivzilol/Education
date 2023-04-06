package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseTable implements Table{

    private Collection<BakedFood> foodOrders;

    private Collection<Drink> drinkOrders;

    private int tableNumber;

    private int capacity;

    private int numberOfPeople;

    private double pricePerPerson;

    private boolean isReserved;

    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        setCapacity(capacity);
        this.tableNumber = tableNumber;
        this.pricePerPerson = pricePerPerson;
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        this.price = numberOfPeople * pricePerPerson;
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double bill = 0;
        for (BakedFood bakedFood : this.foodOrders) {
            bill += bakedFood.getPrice();
        }
        for (Drink drink : this.drinkOrders) {
            bill += drink.getPrice();
        }
        bill += getPrice();
        return bill;
    }

    @Override
    public void clear() {
        isReserved = false;
        this.tableNumber = 0;
        this.price = 0;
        this.drinkOrders.clear();
        this.foodOrders.clear();
    }

    @Override
    public String getFreeTableInfo() {
        StringBuilder sb = new StringBuilder();
        if (!isReserved) {
            sb.append(String.format("Table: %d", tableNumber));
            sb.append(System.lineSeparator());
            sb.append(String.format("Type: %s", getClass().getSimpleName()));
            sb.append(System.lineSeparator());
            sb.append(String.format("Capacity: %d", capacity));
            sb.append(System.lineSeparator());
            sb.append(String.format("Price per person: %.2f", pricePerPerson));
        }
        return sb.toString().trim();
    }
}
