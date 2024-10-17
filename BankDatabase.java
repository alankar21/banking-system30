import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/";
   private static final String DB_name = "banking";
    private static final String USER = "alankar21"; // Replace with your MySQL username
    private static final String PASSWORD = "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"; // Replace with your MySQL password

    public static void main(String[] args) {
        createDatabase();
        createTable();
        insertAccount("John Doe", 1500.00);
        displayAccounts();
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS bank;");
            System.out.println("Database created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS bank.accounts (\n"
                + " id INT AUTO_INCREMENT PRIMARY KEY,\n"
                + " name VARCHAR(100) NOT NULL,\n"
                + " balance DECIMAL(10, 2) NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL + "bank", USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Accounts table created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertAccount(String name, double balance) {
        String sql = "INSERT INTO bank.accounts (name, balance) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL + "bank", USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, balance);
            pstmt.executeUpdate();
            System.out.println("Account added: " + name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAccounts() {
        String sql = i

        try (Connection conn = DriverManager.getConnection(URL + "bank", USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Balance: $" + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
