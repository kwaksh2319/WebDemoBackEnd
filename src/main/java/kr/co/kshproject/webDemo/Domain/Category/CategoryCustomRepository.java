package kr.co.kshproject.webDemo.Domain.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryCustomRepository {
    Optional<Category> findById(Long id);
    List<CategoryDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
