package by.vlad.tasks;

import by.vlad.threads.Cashier;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 extends Thread {

    private List<Cashier> cashiers;
    private Path path;

    public Task1(List<Cashier> cashiers, String filename) {
        this.cashiers = cashiers;
        this.path = Path.of("tasks", "task1", filename);
    }

    @Override
    public void run() {
        try {
            createFile();
            while (true) {
                Thread.sleep(1000L * 10);
                String fileContent = cashiers.stream()
                        .map(Cashier::toString)
                        .collect(Collectors.joining());
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
                    bufferedWriter.write("cashierId,totalOrders,totalSum" + System.lineSeparator());
                    bufferedWriter.write(fileContent);
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private Path createFile() throws IOException {
        Files.createDirectories(Path.of("tasks", "task1"));
        boolean exists = Files.exists(path);
        if (!exists) {
            Files.createFile(path);
        }
        return path;
    }
}
