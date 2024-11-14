package rest;


public class Beverage extends MenuItem {
    public Beverage(int itemId, String itemName, double price, int availableStock) {
        super(itemId, itemName, "Beverage", price, availableStock);
    }

    public double calculatePrice() {
        return getPrice();  // Assuming no additional charges for simplicity
    }
}