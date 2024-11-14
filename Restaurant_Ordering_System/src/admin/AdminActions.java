package admin;
import Customer.Customer;
import rest.MenuItem;

import java.util.List;

public interface AdminActions {
    void createCustomer(int customerId, String name, String email, String phone);
    List<Customer> viewAllCustomers();
    List<MenuItem> viewAllMenuItems();
    boolean exitAdmin();

	void addMenuItem(String itemName, String itemType, double price, int availableStock);

	void removeMenuItem(int itemId);

	void updateMenuItem(int itemId, String newName, String newType, double newPrice, int newStock);
}
