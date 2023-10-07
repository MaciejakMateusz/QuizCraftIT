package pl.coderslab.zadanie_rekrutacyjne_cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.zadanie_rekrutacyjne_cl.service.interfaces.DataTransferService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataTransferService dataTransferService;

    public DataInitializer(DataTransferService dataTransferService) {
        this.dataTransferService = dataTransferService;
    }

    @Override
    public void run(String... args) {
        dataTransferService.transferToDatabase();
    }
}