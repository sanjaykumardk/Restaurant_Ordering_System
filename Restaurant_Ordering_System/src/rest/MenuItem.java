package rest;

public class MenuItem {
    private int itemId;
    private String itemName;
    private String itemType;
    private double price;
    private int availableStock;

    public MenuItem(int itemId, String itemName, String itemType, double price, int availableStock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.availableStock = availableStock;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Name: " + itemName + ", Type: " + itemType + ", Price: $" + price + ", Stock: " + availableStock;
    }

	public double calculatePrice() {
		// TODO Auto-generated method stub
		return 0;
	}
}
