package by.vlad.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Order {

    private List<OrderElement> orderElements;
    private AtomicBoolean isCompleted = new AtomicBoolean(false);

    public Order() {
        Random random = new Random();
        int size = random.nextInt(10) + 1;
        orderElements = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(OrderElement.values().length);
            orderElements.add(OrderElement.values()[index]);
        }
    }

    public List<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(List<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    public AtomicBoolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(AtomicBoolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
