package admin;

import java.sql.*;
import Customer.Customer;
import rest.MenuItem;
import database.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class AdminImpl implements AdminActions {

    private Connection connection;

    public AdminImpl() {
        try {
            connection = DBConnection.getConnection(); // Assuming DBConnection is a utility class that provides a DB connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMenuItem(String itemName, String itemType, double price, int availableStock) {
        String sql = "INSERT INTO MenuItems (item_name, item_type, price, available_stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.setString(2, itemType);
            stmt.setDouble(3, price);
            stmt.setInt(4, availableStock);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item added: " + itemName);
            } else {
                System.out.println("Failed to add menu item.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuItem(int itemId) {
        String sql = "DELETE FROM MenuItems WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item removed with ID: " + itemId);
            } else {
                System.out.println("Menu item with ID " + itemId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMenuItem(int itemId, String newName, String newType, double newPrice, int newStock) {
        String sql = "UPDATE MenuItems SET item_name = ?, item_type = ?, price = ?, available_stock = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setString(2, newType);
            stmt.setDouble(3, newPrice);
            stmt.setInt(4, newStock);
            stmt.setInt(5, itemId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item updated: " + newName);
            } else {
                System.out.println("Menu item with ID " + itemId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> viewAllMenuItems() {
        String sql = "SELECT * FROM MenuItems";
        List<MenuItem> menuItems = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No menu items available.");
            } else {
                while (rs.next()) {
                    int itemId = rs.getInt("item_id");
                    String itemName = rs.getString("item_name");
                    String itemType = rs.getString("item_type");
                    double price = rs.getDouble("price");
                    int availableStock = rs.getInt("available_stock");
                    menuItems.add(new MenuItem(itemId, itemName, itemType, price, availableStock));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    @Override
    public boolean exitAdmin() {
        return false;
    }

    // New methods to manage Customers

    @Override
    public void createCustomer(int customerId, String name, String email, String phone) {
        String sql = "INSERT INTO Customers (customer_id, name, email, phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer created: " + name);
            } else {
                System.out.println("Failed to create customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> viewAllCustomers() {
        String sql = "SELECT * FROM Customers";
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No customers available.");
            } else {
                while (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    customers.add(new Customer(customerId, name, email, phone));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

	 public boolean validateAdminLogin(String username, String password) {
	        String correctUsername = "admin";
	        String correctPassword = "23";
	        return username.equals(correctUsername) && password.equals(correctPassword);
	    }

}


