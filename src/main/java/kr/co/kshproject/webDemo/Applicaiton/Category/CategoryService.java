package kr.co.kshproject.webDemo.Applicaiton.Category;

import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Category.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(CategoryDTO categoryDTO);
    List<Category> findAll();
    Page<Category> findAll(int page, int size);
    Optional<Category> findById(Long id);
    Category update(Long id, CategoryDTO categoryDTO);
    void deleteById(Long id);
    void deleteAll();
}
