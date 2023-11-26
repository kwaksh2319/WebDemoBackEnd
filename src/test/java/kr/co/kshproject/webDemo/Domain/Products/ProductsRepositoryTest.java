package kr.co.kshproject.webDemo.Domain.Products;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductsRepositoryTest {
    @Autowired
    private ProductsRepository productsRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        productsRepository.deleteAll();
    }

    @Test
    public void save() {
        Products product=new Products();

        product.setProductName("testProduct");
        product.setPicture("testPicture");
        product.setDescribe("test");
        product.setPrice(100L);
        product.setSoldOut(false);
        product.setCreatedDate("20231125");
        product.setUpdateDate("20231125");

        product.setCategory(null);

        Products savedProduct = productsRepository.save(product);
        Optional<Products> foundProduct = productsRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("testProduct", foundProduct.get().getProductName());
    }

    @Test
    public void findAll() {
        Products product=new Products();
        product.setProductName("testProduct");
        product.setPicture("testPicture");
        product.setDescribe("test");
        product.setPrice(100L);
        product.setSoldOut(false);
        product.setCreatedDate("20231125");
        product.setUpdateDate("20231125");

        Products savedProducts = productsRepository.save(product);

        List<Products> products = productsRepository.findAll();
        assertFalse(products.isEmpty());
    }

    @Test
    public void findById() {
        Products product=new Products();
        product.setProductName("testProduct");
        product.setPicture("testPicture");
        product.setDescribe("test");
        product.setPrice(100L);
        product.setSoldOut(false);
        product.setCreatedDate("20231125");
        product.setUpdateDate("20231125");
        Products savedProducts = productsRepository.save(product);

        Optional<Products> foundProduct = productsRepository.findById(savedProducts.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("testProduct", foundProduct.get().getProductName());
    }

    @Test
    public void update() {
        Products product=new Products();
        product.setProductName("testProduct");
        product.setPicture("testPicture");
        product.setDescribe("test");
        product.setPrice(100L);
        product.setSoldOut(false);
        product.setCreatedDate("20231125");
        product.setUpdateDate("20231125");
        Products savedProduct = productsRepository.save(product);

        Products productToUpdate = productsRepository.findById(savedProduct.getId()).get();
        productToUpdate.setProductName("Updated Product");
        productsRepository.save(productToUpdate);

       Optional<Products> updatedProduct = productsRepository.findById(savedProduct.getId());
       assertTrue(updatedProduct.isPresent());
        assertEquals("Updated Product", updatedProduct.get().getProductName());
    }

    @Test
    public void deleteAll() {
        Products product=new Products();
        product.setProductName("testProduct");
        product.setPicture("testPicture");
        product.setDescribe("test");
        product.setPrice(100L);
        product.setSoldOut(false);
        product.setCreatedDate("20231125");
        product.setUpdateDate("20231125");
        Products savedProduct = productsRepository.save(product);

        productsRepository.deleteAll();
        List<Products> products = productsRepository.findAll();
        assertTrue(products.isEmpty());
    }
}