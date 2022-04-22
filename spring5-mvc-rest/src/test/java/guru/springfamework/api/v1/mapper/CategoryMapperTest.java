package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private static final String NAME = "fruits";
    private static final long ID = 1L;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.categoryToCategoryDTO(category);

        assertEquals(NAME, category.getName(), "Name of Category must equal to Name of CategoryDTO");
        assertEquals(ID, category.getId(), "Id of Category must equal to Id of CategoryDTO");
    }
}