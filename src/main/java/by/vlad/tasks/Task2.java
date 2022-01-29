package by.vlad.tasks;

import by.vlad.threads.Customer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 extends Thread {

    private List<Customer> customers;
    private Path path;
    public Task2(List<Customer> customers, String filename) {
        this.customers = customers;
        this.path = Path.of("tasks", "task2", filename);
    }

    @Override
    public void run() {
        try {
            createFile();
            while (true) {
                Thread.sleep(1000L * 60 * 2);
                String fileContent = customers.stream()
                        .map(Customer::toString)
                        .collect(Collectors.joining());
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
                    bufferedWriter.write("customerId,totalOrders,avgCalories,avgSum" + System.lineSeparator());
                    bufferedWriter.write(fileContent);
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private Path createFile() throws IOException {
        Files.createDirectories(Path.of("tasks", "task2"));
        boolean exists = Files.exists(path);
        if (!exists) {
            Files.createFile(path);
        }
        return path;
    }
}
