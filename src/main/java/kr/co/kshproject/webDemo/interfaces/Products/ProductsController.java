package kr.co.kshproject.webDemo.interfaces.Products;


import kr.co.kshproject.webDemo.Applicaiton.Product.ProductService;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public ResponseEntity<Products> save(@RequestBody ProductsDTO productsDTO){
        return ResponseEntity.ok(productService.save(productsDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> findAll(){
        return ResponseEntity.ok( productService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( productService.findAll(page,size) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Products>> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductsDTO productsDTO){
        productService.update(id,productsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        productService.deleteAll();
    }
}
