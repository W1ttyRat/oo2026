package ee.tanel.veebipood.repository;

import ee.tanel.veebipood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// @NonNull Category..
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
