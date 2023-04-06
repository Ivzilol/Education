package bakery.repositories.interfaces;

public interface DrinkRepository<T> extends Repository<T> {
    T getByNameAndBrand(String drinkName,String drinkBrand);
}
