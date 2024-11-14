package order;
public class OrderProcessor implements Runnable {
    private Order order;

    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        try {
            // Simulate order processing time
            System.out.println("Processing order ID: " + order.getOrderId());
            Thread.sleep(2000);
            order.setStatus("Completed");
            System.out.println("Order " + order.getOrderId() + " completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
