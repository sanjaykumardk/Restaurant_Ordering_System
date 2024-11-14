package order;

import database.DBConnection; // Make sure to import DBConnection
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    private Connection connection;

    // Constructor with a connection parameter
    public OrdersDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Default constructor that initializes the connection
    public OrdersDAOImpl() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to establish database connection in OrdersDAOImpl.");
        }
    }

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO Orders (customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            stmt.setBigDecimal(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getBigDecimal("total_amount"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getTimestamp("order_date").toLocalDateTime(),
                    rs.getBigDecimal("total_amount"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE Orders SET customer_id = ?, order_date = ?, total_amount = ?, status = ? WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            stmt.setBigDecimal(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setInt(5, order.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
