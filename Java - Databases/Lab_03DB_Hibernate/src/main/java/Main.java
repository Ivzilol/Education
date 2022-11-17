import Entities.Student;
import Entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("emo", 23);
        Student second = new Student("gosho", 23);

        Student first = entityManager.find(Student.class, 1);


        Teacher teacher = new Teacher("petka", LocalDate.now());
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
