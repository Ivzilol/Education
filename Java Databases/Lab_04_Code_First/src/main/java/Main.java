import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Set;

public class    Main {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new  BasicShampoo("shower", label, batch);
        BasicIngredient ingredient = new BasicIngredient(100, "B12");
        BasicIngredient ingredient2 = new BasicIngredient(2, "Violet");

        shampoo.addIngredients(ingredient);
        shampoo.addIngredients(ingredient2);



        entityManager.persist(ingredient);
        entityManager.persist(ingredient2);
        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);

        ProductionBatch batch1 = entityManager.find(ProductionBatch.class, 1);
//        Set<BasicShampoo> shampoos = batch1.getShampoos();
//        shampoos.forEach(System.out::println);


        entityManager.getTransaction().commit();
    }
}
