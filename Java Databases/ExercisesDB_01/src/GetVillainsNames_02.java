import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames_02 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "bbGGbb1234");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);


        PreparedStatement statement = connection.prepareStatement
                ("select name, count(distinct mv.minion_id) AS minion_count from villains AS v\n" +
                        "join minions_villains AS mv on mv.villain_id = v.id\n" +
                        "group by v.name\n" +
                        "having minion_count > ?\n" +
                        "order by minion_count desc;");

        int numberMinions = Integer.parseInt(scanner.nextLine());
        statement.setInt(1, numberMinions);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String villainName = resultSet.getString("name");
            String minionCount = resultSet.getString("minion_count");
            System.out.println(villainName + " " + minionCount);
        }
        connection.close();
        scanner.close();
    }
}
