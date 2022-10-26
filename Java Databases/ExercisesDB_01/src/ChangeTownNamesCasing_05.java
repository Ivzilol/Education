import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing_05 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "bbGGbb1234");

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);

        String countryName = scanner.nextLine();

        PreparedStatement updateTownName = connection.prepareStatement(
                "update towns set name = (name) WHERE country = ?;");
        updateTownName.setString(1, countryName);
        int updateCount = updateTownName.executeUpdate();
        if (updateCount == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected. \n", updateCount);
            PreparedStatement selectTownName = connection.prepareStatement(
                    "SELECT name FROM towns WHERE country = ?");
            selectTownName.setString(1, countryName);
            ResultSet townsSet = selectTownName.executeQuery();
            List<String> towns = new ArrayList<>();
            while (townsSet.next()) {
                String townName = townsSet.getString("name");
                towns.add(townName);
            }
            System.out.println(towns);
            connection.close();
        }
    }
}
