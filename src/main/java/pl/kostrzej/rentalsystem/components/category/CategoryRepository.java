package pl.kostrzej.rentalsystem.components.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    boolean existsByName(String name);

    List<Category> findAll();

    Category findById(long id);
}
