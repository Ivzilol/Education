package wizard_deposits_01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main_01 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("CodeFirstExercises");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();



        entityManager.getTransaction().commit();
        entityManager.close();



    }
}
