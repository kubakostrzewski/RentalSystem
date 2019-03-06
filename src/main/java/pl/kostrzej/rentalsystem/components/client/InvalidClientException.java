package pl.kostrzej.rentalsystem.components.client;

public class InvalidClientException extends RuntimeException {
    InvalidClientException(String message) {
        super(message);
    }
}
