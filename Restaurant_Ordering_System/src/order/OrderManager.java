package order;
public class OrderManager {
    public void processOrder(Order order) {
        Thread orderThread = new Thread(new OrderProcessor(order));
        orderThread.start();
    }
}
