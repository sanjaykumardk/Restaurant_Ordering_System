package order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {
    private Connection connection;

    public OrderItemsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItems (order_item_id, order_id, item_id, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getOrderItemId());
            stmt.setInt(2, orderItem.getOrderId());
            stmt.setInt(3, orderItem.getItemId());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderItems.add(new OrderItem(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE OrderItems SET order_id = ?, item_id = ?, quantity = ? WHERE order_item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getItemId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setInt(4, orderItem.getOrderItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        String sql = "DELETE FROM OrderItems WHERE order_item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderItemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
