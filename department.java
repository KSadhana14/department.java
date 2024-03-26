import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DepartmentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    private static Connection connection;
    private static Statement statement;
    public static void initialize() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
    }
    public static void insertDepartment(Department department) throws SQLException {
        String query = String.format("INSERT INTO department (id, name) VALUES (%d, '%s')",
                                     department.getId(), department.getName());
        statement.executeUpdate(query);
    }
    public static void close() throws SQLException {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
    public static void main(String[] args) {
        try {
            initialize();
            insertDepartment(new Department(1, "Engineering"));
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
}