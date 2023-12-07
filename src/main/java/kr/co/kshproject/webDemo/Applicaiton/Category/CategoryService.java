package kr.co.kshproject.webDemo.Applicaiton.Category;

import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Category.CategoryDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    Category save(CategoryDTO categoryDTO);
    List<CategoryDTO> findAll();
    Map<String,List> findAll(int page, int size);
    Optional<Category> findById(Long id);
    void update(Long id, CategoryDTO categoryDTO);
    void deleteById(Long id);
    void deleteAll();
}
