package pl.coderslab.zadanie_rekrutacyjne_cl.exception;

public class ApiCommunicationException extends RuntimeException{
    public ApiCommunicationException(String message) {
        super(message);
    }
}
