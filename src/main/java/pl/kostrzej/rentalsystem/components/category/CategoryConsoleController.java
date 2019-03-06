package pl.kostrzej.rentalsystem.components.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class CategoryConsoleController implements CategoryController {

    private Scanner scanner;
    private CategoryService categoryService;

    @Override
    public void addCategory() {
        Category category = new Category();
        System.out.println("\nDODAWANIE NOWEJ KATEGORII");
        do {
            System.out.println("Podaj nazwę kategorii:");
            category.setName(scanner.nextLine());
        } while (isCategoryExists(category));
        System.out.println("Podaj opis kategorii:");
        category.setDescription(scanner.nextLine());
        categoryService.addCategory(category);

    }

    @Override
    public void deleteCategory() {
        try {
            showAllCategories();
            System.out.println("Podaj id kategorii:");
            long categoryId = scanner.nextLong();
            deleteCategoryById(categoryId);
        } catch (CategoryListEmptyException e) {
            System.err.println(e.getMessage());
        }
    }

    private void deleteCategoryById(long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showAllCategories() {
        System.out.println("LISTA WSZYSTKICH KATEGORII:");
        System.out.println(String.format("|%-3s|%-20s|%-40s|%-15s|", "ID", "NAZWA", "OPIS", "ILOSC URZADZEN"));
        categoryService.printAll();
    }

    private boolean isCategoryExists(Category category) {
        if (categoryService.isCategoryExists(category)) {
            System.err.println("Kategoria o podanej nazwie już istnieje!");
            return true;
        }
        return false;
    }
}
