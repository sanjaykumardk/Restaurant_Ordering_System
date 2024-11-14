package admin;
import java.sql.*;
import rest.MenuItem;
import database.DBConnection; 

public class Admin {
    private Connection connection;

    public Admin() {
        try {
            connection = DBConnection.getConnection(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItems (item_name, item_type, price, available_stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menuItem.getItemName());
            stmt.setString(2, menuItem.getItemType());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setInt(4, menuItem.getAvailableStock());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item added: " + menuItem.getItemName());
            } else {
                System.out.println("Failed to add menu item.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void updateMenuItem(int itemId, MenuItem newItem) {
        String sql = "UPDATE MenuItems SET item_name = ?, item_type = ?, price = ?, available_stock = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newItem.getItemName());
            stmt.setString(2, newItem.getItemType());
            stmt.setDouble(3, newItem.getPrice());
            stmt.setInt(4, newItem.getAvailableStock());
            stmt.setInt(5, itemId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item updated: " + newItem.getItemName());
            } else {
                System.out.println("Menu item with ID " + itemId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMenu() {
        String sql = "SELECT * FROM MenuItems";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No menu items available.");
            } else {
                while (rs.next()) {
                    int itemId = rs.getInt("item_id");
                    String itemName = rs.getString("item_name");
                    String itemType = rs.getString("item_type");
                    double price = rs.getDouble("price");
                    int availableStock = rs.getInt("available_stock");
                    System.out.println("ID: " + itemId + " - " + itemName + " - " + itemType + " - $" + price + " - Stock: " + availableStock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


