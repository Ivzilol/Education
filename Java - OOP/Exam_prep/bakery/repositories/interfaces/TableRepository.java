package bakery.repositories.interfaces;

public interface TableRepository<T> extends Repository<T> {
    T getByNumber(int number);
}
