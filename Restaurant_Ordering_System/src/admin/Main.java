package admin;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import Customer.CustomerDAOImpl;
import Customer.Customer;
import order.Order;
import order.OrdersDAOImpl;
import rest.MenuItem;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminImpl admin = new AdminImpl(); 
    private static final CustomerDAOImpl customerDAO = new CustomerDAOImpl(); 
    private static final OrdersDAOImpl orderDAO = new OrdersDAOImpl(); 
    


    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void adminLogin() {
        boolean exitAdminMenu = false;
        
        while (!exitAdminMenu) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. View All Menu Items");
            System.out.println("5. Add Customer");
            System.out.println("6. View All Customers");
            System.out.println("7. Exit Admin Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    updateMenuItem();
                    break;
                case 4:
                    viewAllMenuItems();
                    break;
                case 5:
                    addCustomer();
                    break;
                case 6:
                    viewAllCustomers();
                    break;
                case 7:
                    exitAdminMenu = true;
                    System.out.println("Exiting admin menu...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void customerLogin() {
        boolean exitCustomerMenu = false;
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Customer id: ");
        int customerId = scanner.nextInt();
        boolean isValidCustomer = customerDAO.validateCustomerCredentials(customerId, customerName);

        if (!isValidCustomer) {
            System.out.println("Invalid customer credentials! Please try again.");
            return; 
        }

        System.out.println("Login successful! Welcome, " + customerName);
        while (!exitCustomerMenu) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Place Order");
            System.out.println("2. View Orders");
            System.out.println("3. Pay Bill");
            System.out.println("4. Checkout");
            System.out.println("5. Exit Customer Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                	  System.out.print("Enter Customer ID: ");
                      int customerId1 = scanner.nextInt();
                      scanner.nextLine();
                      System.out.print("Enter Order Date (YYYY-MM-DD HH:MM:SS): ");
                      String orderDateStr = scanner.nextLine();
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                      LocalDateTime orderDate = LocalDateTime.parse(orderDateStr, formatter);  
                      System.out.print("Enter Total Amount: ");
                      BigDecimal totalAmount = scanner.nextBigDecimal();
                      scanner.nextLine(); 
                      System.out.print("Enter Order Status (e.g., Pending): ");
                      String status = scanner.nextLine();
                      Order order = new Order(0, customerId1, orderDate, totalAmount, status);  
                      orderDAO.addOrder(order);

                      System.out.println("Order placed successfully with Order ID: " + order.getOrderId());
                 
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    payBill();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    exitCustomerMenu = true;
                    System.out.println("Exiting customer menu...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }


    private static void addMenuItem() {
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Item Type (e.g., Beverage, MainCourse): ");
        String itemType = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Available Stock: ");
        int availableStock = scanner.nextInt();
        scanner.nextLine(); 
        admin.addMenuItem(itemName, itemType, price, availableStock);
    }

    private static void removeMenuItem() {
        System.out.print("Enter Item ID to remove: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();  

        admin.removeMenuItem(itemId);
    }

    private static void updateMenuItem() {
        System.out.print("Enter Item ID to update: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter New Item Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Item Type: ");
        String newType = scanner.nextLine();
        System.out.print("Enter New Price: ");
        double newPrice = scanner.nextDouble();
        System.out.print("Enter New Available Stock: ");
        int newStock = scanner.nextInt();
        scanner.nextLine();  

        admin.updateMenuItem(itemId, newName, newType, newPrice, newStock);
    }

    private static void viewAllMenuItems() {
        List<MenuItem> menuItems = admin.viewAllMenuItems();
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
        } else {
            System.out.println("\nAll Menu Items:");
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Customer Phone: ");
        String phone = scanner.nextLine();

        admin.createCustomer(customerId, name, email, phone);
    }

    private static void viewAllCustomers() {
        List<Customer> customers = admin.viewAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("\nAll Customers:");
            for (Customer customer : customers) {
                System.out.println(customer.getCustomerId() + " - " + customer.getName() + " - " + customer.getEmail() + " - " + customer.getPhone());
            }
        }
    }
    private static void placeOrder() {
        System.out.print("Enter Customer ID for Order: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Order Total Amount: ");
        BigDecimal totalAmount = scanner.nextBigDecimal();
        scanner.nextLine(); 

        System.out.print("Enter Order Status (e.g., Pending, Completed): ");
        String status = scanner.nextLine();

        List<MenuItem> orders;
		Order order = new Order( 1, customerId, LocalDateTime.now(), totalAmount, status);
        customerDAO.addOrderToCustomer(customerId, order);
    }

    private static void viewOrders() {
        System.out.print("Enter Customer ID to view orders: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  

        customerDAO.viewCustomerOrders(customerId);
    }

    private static void payBill() {
        System.out.print("Enter Order ID to pay bill for: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter Payment Amount: ");
        BigDecimal paymentAmount = scanner.nextBigDecimal();
        scanner.nextLine();  // Consume newline

        customerDAO.processPayment(orderId, paymentAmount);
    }

    private static void checkout() {
        System.out.print("Enter Customer ID to checkout: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        customerDAO.checkout(customerId);
    }

}
