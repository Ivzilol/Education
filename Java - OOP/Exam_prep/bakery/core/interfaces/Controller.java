package bakery.core.interfaces;

public interface Controller {
   String addFood(String type, String name, double price);

   String addDrink(String type, String name, int portion, String brand);

   String addTable(String type, int tableNumber, int capacity);

   String reserveTable(int numberOfPeople);

   String orderFood(int tableNumber, String foodName);

   String orderDrink(int tableNumber, String drinkName, String drinkBrand);

   String leaveTable(int tableNumber);

   String getFreeTablesInfo();

   String getTotalIncome();
}
