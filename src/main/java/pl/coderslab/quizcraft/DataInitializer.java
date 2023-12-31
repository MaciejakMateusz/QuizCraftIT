package pl.coderslab.quizcraft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.quizcraft.service.DataTransferService;

import java.util.stream.IntStream;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataTransferService dataTransferService;

    public DataInitializer(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @Override
    public void run(String... args) {
        IntStream.rangeClosed(1, 10).forEach(i -> dataTransferService.transferToDatabase());
    }
}