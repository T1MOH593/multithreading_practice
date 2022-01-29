package by.vlad;

import by.vlad.orders.Order;
import by.vlad.tasks.Task1;
import by.vlad.tasks.Task2;
import by.vlad.tasks.Task3;
import by.vlad.threads.Cashier;
import by.vlad.threads.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {

    @Builder.Default
    private String task1Filename = "resultTask1";
    @Builder.Default
    private String task2Filename = "resultTask2";
    @Builder.Default
    private int numberOfCashiers = 100;
    @Builder.Default
    private int numberOfCustomers = 100;
    @Builder.Default
    private int ordersCapacity = 300;

    public void start() {
        BlockingQueue<Order> orders = new ArrayBlockingQueue<>(ordersCapacity);
        List<Customer> customers = new ArrayList<>(numberOfCustomers);
        List<Cashier> cashiers = new ArrayList<>(numberOfCashiers);
        for (int i = 0; i < numberOfCashiers; i++) {
            Cashier cashier = new Cashier(orders);
            cashiers.add(cashier);
            cashier.start();
        }
        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer = new Customer(orders);
            customers.add(customer);
            customer.start();
        }
        Task1 task1 = new Task1(cashiers, task1Filename);
        task1.start();
        Task2 task2 = new Task2(customers, task2Filename);
        task2.start();
        Task3 task3 = new Task3(task1Filename, task2Filename);
        task3.start();
    }
}
