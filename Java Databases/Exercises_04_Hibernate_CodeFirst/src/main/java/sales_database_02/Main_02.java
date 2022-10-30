package sales_database_02;

import sales_database_02.entities.Customer;
import sales_database_02.entities.Product;
import sales_database_02.entities.Sale;
import sales_database_02.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main_02 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("CodeFirstExercises");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product("product", 123, BigDecimal.TEN);
        Customer customer = new Customer("customer", "customer", "customer");
        StoreLocation location = new StoreLocation("location");
        Sale sale = new Sale(product, customer, location);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(location);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
