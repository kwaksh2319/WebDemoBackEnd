package kr.co.kshproject.webDemo.Applicaiton.Category;

import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Category.CategoryCustomRepository;
import kr.co.kshproject.webDemo.Domain.Category.CategoryDTO;
import kr.co.kshproject.webDemo.Domain.Category.CategoryRepository;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private final CategoryCustomRepository categoryCustomRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryCustomRepository categoryCustomRepository){
        this.categoryRepository=categoryRepository;
        this.categoryCustomRepository=categoryCustomRepository;
    }

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category=new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return categoryCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryCustomRepository.findById(id);
    }

    @Override
    public void update(Long id, CategoryDTO categoryDTO) {
         categoryRepository.save(ConvertEntity(id,categoryDTO));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    private Category ConvertEntity(Long id, CategoryDTO categoryDTO){

        Optional<Category> category=categoryCustomRepository.findById(id);
         Long parentCategoryId=categoryDTO.getParentCategoryId();
        Optional<Category>  parentCategory=findById(parentCategoryId);
        category.get().setCategoryName(categoryDTO.getCategoryName());
        category.get().setCreatedDate(categoryDTO.getCreatedDate());
        category.get().setUpdateDate(categoryDTO.getUpdateDate());

        if(parentCategory.isPresent()==true){
            category.get().setParentCategory(parentCategory.get());
        }

        return category.get();
    }
}
