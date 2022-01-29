package by.vlad.threads;

import by.vlad.orders.Order;
import by.vlad.orders.OrderElement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Customer extends Thread {

    private static AtomicInteger id = new AtomicInteger(0);

    private Integer customerId;
    private Integer totalOrders = 0;
    private Integer totalCalories = 0;
    private Integer totalSum = 0;

    private BlockingQueue<Order> orders;

    public Customer(BlockingQueue<Order> orders) {
        this.orders = orders;
        customerId = id.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            Order order = new Order();
            orders.add(order);
            int calories = 0;
            Integer sum = 0;
            for (OrderElement orderElement : order.getOrderElements()) {
                calories += orderElement.getCalories();
                sum += orderElement.getPrice();
            }

            while (!order.getIsCompleted().get()) {
            }
            totalOrders++;
            totalCalories += calories;
            totalSum += sum;
        }
    }

    @Override
    public String toString() {
        return customerId + ","
                + totalOrders + ","
                + totalCalories * 1.0 / totalOrders + ","
                + totalSum * 1.0 / totalOrders
                + System.lineSeparator();
    }
}
