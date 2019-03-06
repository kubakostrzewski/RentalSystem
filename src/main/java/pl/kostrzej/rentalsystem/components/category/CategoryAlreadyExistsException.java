package pl.kostrzej.rentalsystem.components.category;

public class CategoryAlreadyExistsException extends RuntimeException {
    CategoryAlreadyExistsException() {
        super("Kategoria o podanej nazwie już istnieje!");
    }
}
