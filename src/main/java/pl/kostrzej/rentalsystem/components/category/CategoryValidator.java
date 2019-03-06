package pl.kostrzej.rentalsystem.components.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {

    @Autowired
    private CategoryRepository categoryRepository;

    public void validate(Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new CategoryAlreadyExistsException();
        if (category.getName().isEmpty())
            throw new NullPointerException("Nazwa kategorii nie moż być pusta!");
    }
}
