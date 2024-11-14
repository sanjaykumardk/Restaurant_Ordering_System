	package order;
	
	import java.util.List;
	
	public interface OrdersDAO {
	 void addOrder(Order order);
	 Order getOrderById(int orderId);
	 List<Order> getAllOrders();
	 void updateOrder(Order order);
	 void deleteOrder(int orderId);
	}
