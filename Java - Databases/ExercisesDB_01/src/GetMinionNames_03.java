import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames_03 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "bbGGbb1234");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

        int villainId = Integer.parseInt(scanner.nextLine());
        PreparedStatement villainStatement = connection.prepareStatement
                ("SELECT name FROM villains WHERE id= ?");
        villainStatement.setInt(1, villainId);

        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;

        }
        String villainName = villainSet.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement minionsStatement = connection.prepareStatement("""
                select name, age\s
                from minions AS m
                join minions_villains AS mv on mv.minion_id = m.id
                where mv.villain_id = ?""");
        minionsStatement.setInt(1, villainId);
        ResultSet minionSet = minionsStatement.executeQuery();

        for (int i = 0; minionSet.next(); i++) {
            String name = minionSet.getString("name");
            int age = minionSet.getInt("age");
            System.out.printf("%d. %s %d\n", i, name, age);
        }
        connection.close();
    }
}
