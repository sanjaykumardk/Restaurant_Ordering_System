package Customer;
import order.Order;

public interface CustomerDAO{
    void addCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    void addOrderToCustomer(int customerId, Order order);
    void viewCustomerOrders(int customerId);
}