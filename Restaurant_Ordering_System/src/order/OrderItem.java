package order;
public class OrderItem {
    private int orderItemId;
    private int orderId;
    public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int itemId;
    private int quantity;
	public OrderItem(int orderItemId, int orderId, int itemId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

    // Constructors, getters, and setters omitted for brevity
}