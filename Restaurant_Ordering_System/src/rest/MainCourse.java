package rest;


public class MainCourse extends MenuItem {
    public MainCourse(int itemId, String itemName, double price, int availableStock) {
        super(itemId, itemName, "MainCourse", price, availableStock);
    }

    @Override
    public double calculatePrice() {
        return getPrice();  // Additional logic for main courses can be added later
    }
}

