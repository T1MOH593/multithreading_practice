package by.vlad.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Task3 extends Thread {

    private Path task1Path;
    private Path task2Path;

    public Task3(String fileName1, String fileName2) {
        this.task1Path = Path.of("tasks", "task1", fileName1);
        this.task2Path = Path.of("tasks", "task2", fileName2);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000L * 60);
                System.out.println("The best cashier has id = " + getBestCashier());
                System.out.println("The vest customer has id = " + getBestCustomer());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getBestCashier() throws IOException {
        List<String> strings = Files.readAllLines(task1Path);
        strings.remove(0);
        Optional<String[]> max = strings.stream()
                .map(line -> line.split(","))
                .max((arr1, arr2) -> {
                    int totalOrders1 = Integer.parseInt(arr1[1]);
                    int totalSum1 = Integer.parseInt(arr1[2]);
                    int totalOrders2 = Integer.parseInt(arr2[1]);
                    int totalSum2 = Integer.parseInt(arr2[2]);
                    double avgSum1 = totalSum1 * 1.0 / totalOrders1;
                    double avgSum2 = totalSum2 * 1.0 / totalOrders2;
                    return Double.compare(avgSum2, avgSum1);
                });
        return max.orElse(new String[]{"0"})[0];
    }

    private String getBestCustomer() throws IOException {
        List<String> strings = Files.readAllLines(task2Path);
        strings.remove(0);
        Optional<String[]> max = strings.stream()
                .map(line -> line.split(","))
                .max((arr1, arr2) -> {
                    int totalOrders1 = Integer.parseInt(arr1[1]);
                    double avgCalories1 = Double.parseDouble(arr1[2]);
                    int totalOrders2 = Integer.parseInt(arr2[1]);
                    double avgCalories2 = Double.parseDouble(arr2[2]);
                    double totalCalories1 = totalOrders1 * avgCalories1;
                    double totalCalories2 = totalOrders2 * avgCalories2;

                    return Double.compare(totalCalories2, totalCalories1);
                });
        return max.orElse(new String[]{"0"})[0];
    }
}
