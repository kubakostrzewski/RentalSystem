package pl.kostrzej.rentalsystem.components.client;

public class ClientAlreadyExistsException extends RuntimeException {
    ClientAlreadyExistsException(String message) {
        super(message);
    }
}
