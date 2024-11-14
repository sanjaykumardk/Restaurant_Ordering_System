package Customer;

import order.Order;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import database.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String USER = "sanjaydk";
    private static final String PASSWORD = "123abc";

    public CustomerDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO Customers (customer_id, name, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.executeUpdate();
            System.out.println("Customer added: " + customer);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM Customers WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                return new Customer(customerId, name, email, phone);
            } else {
                System.out.println("Customer with ID " + customerId + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addOrderToCustomer(int customerId, Order order) {
        String query = "INSERT INTO Orders (order_id, customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, customerId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
            preparedStatement.setBigDecimal(4, order.getTotalAmount());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Order added for customer ID " + customerId + ": " + order);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewCustomerOrders(int customerId) {
        String query = "SELECT * FROM Orders WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                LocalDateTime orderDate = resultSet.getTimestamp("order_date").toLocalDateTime();
                BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                String status = resultSet.getString("status");
                orders.add(new Order(orderId, customerId, orderDate, totalAmount, status));
            }

            if (orders.isEmpty()) {
                System.out.println("No orders found for customer ID " + customerId);
            } else {
                System.out.println("Orders for customer ID " + customerId + ":");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateCustomerCredentials(int customerId, String customerName) {
        String query = "SELECT COUNT(*) FROM Customers WHERE customer_id = ? AND name = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, customerId);
            stmt.setString(2, customerName);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public void processPayment(int orderId, BigDecimal paymentAmount) {
        // TODO Auto-generated method stub
    }

    public void checkout(int customerId) {
        // TODO Auto-generated method stub
    }
}
