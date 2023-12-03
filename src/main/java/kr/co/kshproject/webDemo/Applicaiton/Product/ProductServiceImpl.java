package kr.co.kshproject.webDemo.Applicaiton.Product;

import kr.co.kshproject.webDemo.Applicaiton.Category.CategoryService;
import kr.co.kshproject.webDemo.Domain.Category.Category;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsCustomRepository;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;
import kr.co.kshproject.webDemo.Domain.Products.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductsRepository productsRepository;

    private final CategoryService categoryService;
    private final ProductsCustomRepository productsCustomRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,ProductsCustomRepository productsCustomRepository,CategoryService categoryService){
        this.productsRepository=productsRepository;
        this.productsCustomRepository=productsCustomRepository;
        this.categoryService=categoryService;
    }

    @Override
    public Products save(ProductsDTO productsDTO) {
        Products product=new Products(productsDTO);
        return productsRepository.save(product);
    }

    @Override
    public List<ProductsDTO> findAll() {
        return productsCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return productsCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Products> findById(Long id) {
        return productsCustomRepository.findById(id);
    }

    @Override
    public Products update(Long id, ProductsDTO productsDTO) {
        return productsRepository.save(ConverEntity(id,productsDTO));
    }

    @Override
    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productsRepository.deleteAll();
    }

    private Products ConverEntity(Long id, ProductsDTO productsDTO){

        Optional<Products> product=productsCustomRepository.findById(id);
        Long category_id=productsDTO.getId();
        Optional<Category> updateCategory =categoryService.findById(category_id);
        product.get().setProductName(productsDTO.getProductName());
        product.get().setDescribe(productsDTO.getDescribe());
        product.get().setPicture(productsDTO.getPicture());
        product.get().setSoldOut(productsDTO.getSoldOut());
        product.get().setCreatedDate(productsDTO.getCreatedDate());
        product.get().setUpdateDate(productsDTO.getUpdateDate());
        product.get().setPrice(productsDTO.getPrice());

        if(updateCategory.isPresent()==true){
            product.get().setCategory( updateCategory.get());
        }

        return product.get();
    }
}
