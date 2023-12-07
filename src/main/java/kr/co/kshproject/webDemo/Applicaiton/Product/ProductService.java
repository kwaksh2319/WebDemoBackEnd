package kr.co.kshproject.webDemo.Applicaiton.Product;

import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Products save(ProductsDTO productsDTO);
    List<ProductsDTO>  findAll();
    Map<String,List> findAll(int page, int size);
    Optional<Products> findById(Long id);
    void update(Long id, ProductsDTO productsDTO);
    void deleteById(Long id);
    void deleteAll();
}
