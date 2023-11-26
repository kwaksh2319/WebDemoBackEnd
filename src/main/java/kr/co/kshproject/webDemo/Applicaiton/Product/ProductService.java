package kr.co.kshproject.webDemo.Applicaiton.Product;

import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Products save(Products product);
    List<Products> findAll();
    Page<Products> findAll(int page, int size);
    Optional<Products> findById(Long id);
    Products update(Long id, ProductsDTO productsDTO);
    void deleteById(Long id);
    void deleteAll();
}
