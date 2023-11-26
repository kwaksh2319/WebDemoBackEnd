package kr.co.kshproject.webDemo.Domain.Category;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        categoryRepository.deleteAll();
    }

    @Test
    public void save() {
        Category category=new Category();
        category.setCategoryName("test");
        category.setParentCategory(null);
        category.setCreatedDate("20231125");
        category.setUpdateDate("20231125");

        Category savedCategory = categoryRepository.save(category);
        Optional<Category> foundCategory = categoryRepository.findById(savedCategory.getId());

        assertTrue(foundCategory.isPresent());
        assertEquals("test", foundCategory.get().getCategoryName());
    }

    @Test
    public void findAll() {
        Category category=new Category();
        category.setCategoryName("test");
        category.setParentCategory(null);
        category.setCreatedDate("20231125");
        category.setUpdateDate("20231125");

        Category savedCategory = categoryRepository.save(category);

        List<Category> categories  = categoryRepository.findAll();
        assertFalse(categories.isEmpty());
    }

    @Test
    public void findById() {
        Category category=new Category();
        category.setCategoryName("test");
        category.setParentCategory(null);
        category.setCreatedDate("20231125");
        category.setUpdateDate("20231125");

        Category savedCategory = categoryRepository.save(category);

        Optional<Category> foundCategory= categoryRepository.findById(savedCategory.getId());
        assertTrue(foundCategory.isPresent());
        assertEquals("test", foundCategory.get().getCategoryName());
    }

    @Test
    public void update() {
        Category category=new Category();
        category.setCategoryName("test");
        category.setParentCategory(null);
        category.setCreatedDate("20231125");
        category.setUpdateDate("20231125");

        Category savedCategory = categoryRepository.save(category);

        Category categoryToUpdate = categoryRepository.findById(savedCategory.getId()).get();
        categoryToUpdate.setCategoryName("update test");
        categoryRepository.save(categoryToUpdate);

        Optional<Category> updatedCategory = categoryRepository.findById(savedCategory.getId());
        assertTrue(updatedCategory.isPresent());
        assertEquals("update test", updatedCategory.get().getCategoryName());
    }

    @Test
    public void deleteAll() {
        Category category=new Category();
        category.setCategoryName("test");
        category.setParentCategory(null);
        category.setCreatedDate("20231125");
        category.setUpdateDate("20231125");

        Category savedCategory = categoryRepository.save(category);
        categoryRepository.deleteAll();
        List<Category> categories = categoryRepository.findAll();
        assertTrue(categories.isEmpty());
    }
}