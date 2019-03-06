package pl.kostrzej.rentalsystem.components.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryValidator categoryValidator;

    public void addCategory(Category category) {
        categoryValidator.validate(category);
        categoryRepository.existsByName(category.getName());

    }

    public void deleteCategoryById(long categoryId) {
        if (categoryRepository.existsById(categoryId))
            categoryRepository.deleteById(categoryId);
        else throw new EntityNotFoundException("Kategoria o podanym ID nie istnieje!");
    }

    public boolean isCategoryExists(Category category) {
        return categoryRepository.existsByName(category.getName());
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) return categories;
        throw new CategoryListEmptyException();
    }

    @Transactional
    public void printAll() {
        getAllCategories().forEach(category -> System.out.println(String.format("|%-3d|%-20s|%-40s|%-15s|",
                category.getId(), category.getName(), category.getDescription(), category.getDevices().size())));
    }
}
