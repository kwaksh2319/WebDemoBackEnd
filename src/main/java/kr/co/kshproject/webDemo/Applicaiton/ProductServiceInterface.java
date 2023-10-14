package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Product;

import java.util.List;

public interface ProductServiceInterface {

    public void save(Product product);

    public void deleteProductDetail(Long id);
    public void update(Long id, Product newProduct);
    public List<Product> findAll(int page, int size);

    public Product findDetail(Long id);
}
