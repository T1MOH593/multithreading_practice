package by.vlad.threads;

import by.vlad.orders.Order;
import by.vlad.orders.OrderElement;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Cashier extends Thread {

    private static final AtomicInteger id = new AtomicInteger(0);

    private Integer cashierId;
    private Integer totalOrders = 0;
    private Integer totalSum = 0;

    private BlockingQueue<Order> orders;

    @Override
    public void run() {
        try {
            while (true) {
                Order order = orders.poll();
                if (order != null) {
                    Thread.sleep(1000L * order.getOrderElements().size());
                    totalOrders++;
                    for (OrderElement orderElement : order.getOrderElements()) {
                        totalSum += orderElement.getPrice();
                    }
                    order.getIsCompleted().set(true);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cashier(BlockingQueue<Order> orders) {
        this.orders = orders;
        cashierId = id.incrementAndGet();
    }

    @Override
    public String toString() {
        return cashierId + ","
                + totalOrders + ","
                + totalSum
                + System.lineSeparator();
    }
}
