
import java.sql.*;
import java.util.*;

public class MainAtm {
    private static Scanner sc = new Scanner(System.in);
    private static Connection conn = DatabaseConnection.getConnection();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n💳 WELCOME TO ATM MACHINE 💳");
            System.out.println("1️⃣  Login to Existing Account");
            System.out.println("2️⃣  Create New Account");
            System.out.println("3️⃣  Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> loginUser();
                case 2 -> createAccount();
                case 3 -> {
                    System.out.println("🙏 Thank you for using our ATM. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        try {
            System.out.print("Set a 4-digit PIN: ");
            int pin = sc.nextInt();

            if (pin < 1000 || pin > 9999) {
                System.out.println("⚠️ PIN must be 4 digits.");
                return;
            }

            long atmNumber = generateAtmNumber();

            String query = "INSERT INTO Users (atm_number, atm_pin, balance) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, atmNumber);
            ps.setInt(2, pin);
            ps.setDouble(3, 0.0);
            ps.executeUpdate();

            System.out.println("✅ Account Created Successfully!");
            System.out.println("🪪 Your ATM Number: " + atmNumber);
            System.out.println("🔐 Remember Your PIN: " + pin);
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private static long generateAtmNumber() {
        Random rand = new Random();
        long number;
        while (true) {
            number = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
            try {
                String check = "SELECT atm_number FROM Users WHERE atm_number = ?";
                PreparedStatement ps = conn.prepareStatement(check);
                ps.setLong(1, number);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) break; // unique number found
            } catch (Exception e) {
                System.out.println("Error generating ATM number: " + e.getMessage());
            }
        }
        return number;
    }

    private static void loginUser() {
        try {
            System.out.print("Enter ATM Number: ");
            long atmNumber = sc.nextLong();
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            String query = "SELECT * FROM Users WHERE atm_number = ? AND atm_pin = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, atmNumber);
            ps.setInt(2, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Login Successful!\n");
                AtmOperations atm = new AtmOperations(atmNumber);
                atmMenu(atm);
            } else {
                System.out.println("❌ Invalid ATM Number or PIN.");
            }
        } catch (Exception e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    private static void atmMenu(AtmOperations atm) {
        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. View Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> atm.viewBalance();
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    atm.deposit(sc.nextDouble());
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    atm.withdraw(sc.nextDouble());
                }
                case 4 -> atm.viewMiniStatement();
                case 5 -> {
                    System.out.println("🙏 Thank you! Please collect your card.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }
}




