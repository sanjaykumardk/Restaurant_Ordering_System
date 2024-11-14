package Customer;
import java.util.ArrayList;
import java.util.List;

import order.Order;

public class CustomerOrder {
    private Customer customer;
    private List<Order> orders;

    public CustomerOrder(Customer customer) {
        this.customer = customer;
        this.orders = new ArrayList<>();
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Add an order to the customer's list of orders
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    // Display orders for the customer
    public void viewOrders() {
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }
}
