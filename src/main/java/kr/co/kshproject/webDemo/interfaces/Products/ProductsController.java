package kr.co.kshproject.webDemo.interfaces.Products;


import kr.co.kshproject.webDemo.Applicaiton.Product.ProductService;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Products> save(@RequestBody Products product){
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Products>> findAll(){
        return ResponseEntity.ok( productService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<Products>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( productService.findAll(page,size) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> update(@PathVariable Long id, @RequestBody ProductsDTO productsDTO){
        return ResponseEntity.ok(productService.update(id,productsDTO));
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
