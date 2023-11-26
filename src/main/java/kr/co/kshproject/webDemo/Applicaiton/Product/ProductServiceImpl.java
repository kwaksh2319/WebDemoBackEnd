package kr.co.kshproject.webDemo.Applicaiton.Product;

import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Products.ProductsDTO;
import kr.co.kshproject.webDemo.Domain.Products.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository){
        this.productsRepository=productsRepository;
    }

    @Override
    public Products save(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Page<Products> findAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return productsRepository.findAll(pageable);
    }

    @Override
    public Optional<Products> findById(Long id) {
        return productsRepository.findById(id);
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
        Optional<Products> product=productsRepository.findById(id);
        product.get().setProductName(productsDTO.getProductName());
        product.get().setCategory(productsDTO.getCategory());
        product.get().setDescribe(productsDTO.getDescribe());
        product.get().setPicture(productsDTO.getPicture());
        product.get().setSoldOut(productsDTO.getSoldOut());
        product.get().setCreatedDate(productsDTO.getCreatedDate());
        product.get().setUpdateDate(productsDTO.getUpdateDate());
        product.get().setPrice(productsDTO.getPrice());

        return product.get();
    }
}
