
import java.sql.*;

public class AtmOperations {
    private Connection conn;
    private long atmNumber;

    public AtmOperations(long atmNumber) {
        this.atmNumber = atmNumber;
        conn = DatabaseConnection.getConnection();
    }

    public void viewBalance() {
        try {
            String query = "SELECT balance FROM Users WHERE atm_number = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, atmNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("💰 Available Balance: ₹" + rs.getDouble("balance"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deposit(double amount) {
        try {
            String update = "UPDATE Users SET balance = balance + ? WHERE atm_number = ?";
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setDouble(1, amount);
            ps.setLong(2, atmNumber);
            ps.executeUpdate();
            recordTransaction("Deposit", amount);
            System.out.println("✅ Deposited ₹" + amount + " successfully!");
            viewBalance();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(double amount) {
        try {
            String check = "SELECT balance FROM Users WHERE atm_number = ?";
            PreparedStatement ps = conn.prepareStatement(check);
            ps.setLong(1, atmNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if (amount <= currentBalance && amount % 100 == 0) {
                    String update = "UPDATE Users SET balance = balance - ? WHERE atm_number = ?";
                    PreparedStatement ps2 = conn.prepareStatement(update);
                    ps2.setDouble(1, amount);
                    ps2.setLong(2, atmNumber);
                    ps2.executeUpdate();
                    recordTransaction("Withdraw", amount);
                    System.out.println("💸 Withdrawn ₹" + amount + " successfully!");
                    viewBalance();
                } else {
                    System.out.println("⚠️ Insufficient balance or invalid amount.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewMiniStatement() {
        try {
            String query = "SELECT type, amount, date_time FROM Transactions WHERE atm_number = ? ORDER BY date_time DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, atmNumber);
            ResultSet rs = ps.executeQuery();
            System.out.println("📜 MINI STATEMENT:");
            while (rs.next()) {
                System.out.println(rs.getString("date_time") + " | " + rs.getString("type") + " | ₹" + rs.getDouble("amount"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void recordTransaction(String type, double amount) throws SQLException {
        String insert = "INSERT INTO Transactions(atm_number, type, amount) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setLong(1, atmNumber);
        ps.setString(2, type);
        ps.setDouble(3, amount);
        ps.executeUpdate();
    }
}
