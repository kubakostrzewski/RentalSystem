package pl.kostrzej.rentalsystem.components.category;

public class CategoryListEmptyException extends RuntimeException {
    CategoryListEmptyException() {
        super("Lista kategorii jest pusta!");
    }
}
