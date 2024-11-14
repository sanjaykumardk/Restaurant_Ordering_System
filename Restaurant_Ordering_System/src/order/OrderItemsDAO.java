package order;

import java.util.List;

public interface OrderItemsDAO {
 void addOrderItem(OrderItem orderItem);
 List<OrderItem> getOrderItemsByOrderId(int orderId);
 void updateOrderItem(OrderItem orderItem);
 void deleteOrderItem(int orderItemId);
}
