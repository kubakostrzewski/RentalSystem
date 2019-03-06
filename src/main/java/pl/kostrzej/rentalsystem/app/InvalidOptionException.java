package pl.kostrzej.rentalsystem.app;

public class InvalidOptionException extends RuntimeException {
    InvalidOptionException() {
        super("Nie ma takiej opcji!");
    }
}
