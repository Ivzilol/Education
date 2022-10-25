import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {


        createConnection("root", "bbGGbb1234", "custom-orm");
        Connection connection = getConnection();
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);


        User user = new User("pesho", 25, LocalDate.now());

        user.setUsername("pesho_new_final2");
//        userEntityManager.doCreate(User.class);
//        userEntityManager.doAlter(User.class);

        userEntityManager.persist(user);

        Iterable<User> first = userEntityManager.find(User.class);

        System.out.println(first.toString());

        User toDelete = userEntityManager.findFirst(User.class, "id > 3");
        System.out.println(toDelete);

        userEntityManager.delete(user);

        Iterable<User> second = userEntityManager.find(User.class);

        System.out.println(second.toString());

        connection.close();

    }
}
