package kr.co.kshproject.webDemo.Domain.Products;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductsCustomRepository {
    Optional<Products> findById(Long id);
    List<ProductsDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
