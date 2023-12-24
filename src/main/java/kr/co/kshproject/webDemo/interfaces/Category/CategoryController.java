package kr.co.kshproject.webDemo.interfaces.Category;

import kr.co.kshproject.webDemo.Applicaiton.Category.CategoryService;
import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Category.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> save(@Validated @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok( categoryService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@Min(1) @PathVariable int page, @Range(min=1,max=100) @PathVariable int size){
        return ResponseEntity.ok( categoryService.findAll(page,size) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findById(@Min(1) @PathVariable Long id){
        Optional<Category> category=categoryService.findById(id);
        return ResponseEntity.ok( category );
    }

    @PutMapping("/{id}")
    public void update(@Min(1) @PathVariable Long id,@Validated @RequestBody CategoryDTO categoryDTO){
        categoryService.update(id,categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
        categoryService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        categoryService.deleteAll();
    }
}
