package kr.co.kshproject.webDemo.Applicaiton.Category;

import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Category.CategoryDTO;
import kr.co.kshproject.webDemo.Domain.Category.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category=new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.save(ConverEntity(id,categoryDTO));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    private Category ConverEntity(Long id, CategoryDTO categoryDTO){
        Optional<Category> category=categoryRepository.findById(id);
        category.get().setCategoryName(categoryDTO.getCategoryName());

       // category.get().setParentCategory(categoryDTO.getParentCategory());
        category.get().setCreatedDate(categoryDTO.getCreatedDate());
        category.get().setUpdateDate(categoryDTO.getUpdateDate());

        return category.get();
    }
}
