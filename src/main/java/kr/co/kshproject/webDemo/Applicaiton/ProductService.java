package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Product;
import kr.co.kshproject.webDemo.Domain.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService implements ProductServiceInterface{
    @Autowired
    private ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }

    public void deleteProductDetail(Long id){
        productDao.delete(id);
    }
    public void update(Long id, Product newProduct){
        productDao.update(id,newProduct);
    }
    public List<Product> findAll(int page, int size) {
        return productDao.findAll(page,size);
    }

    public Product findDetail(Long id) {
        return productDao.findDetail(id);
    }
}
