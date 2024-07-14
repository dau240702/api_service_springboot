package hodau.backendapi.com.shopnoithat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hodau.backendapi.com.shopnoithat.dto.CategoryDTO;
import hodau.backendapi.com.shopnoithat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new hodau.backendapi.com.shopnoithat.dto.CategoryDTO(c.categoryId, c.name, c.description, c.parent.categoryId) FROM Category c WHERE c.categoryId = :categoryId")
    CategoryDTO findCategoryDTOById(@Param("categoryId") Long categoryId);
}
