import java.sql.*;

public class JdbcDemo {
    private static final String URL = "jdbc:ignite:thin://127.0.0.1";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from table");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

    }
}
