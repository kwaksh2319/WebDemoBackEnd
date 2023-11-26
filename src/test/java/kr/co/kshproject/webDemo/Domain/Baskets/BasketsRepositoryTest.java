package kr.co.kshproject.webDemo.Domain.Baskets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BasketsRepositoryTest {
    @Autowired
    private BasketsRepository basketsRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        basketsRepository.deleteAll();
    }

    @Test
    public void save() {
        Baskets basket=new Baskets();
        basket.setProductName("test");
        basket.setQuantity(1L);
        basket.setProductStatus("T");
        basket.setProducts(null);
        basket.setUsers(null);
        basket.setCreateDate("20231125");
        basket.setUpdateDate("20231125");

        Baskets savedBasket = basketsRepository.save(basket);
        Optional<Baskets> foundBBasket= basketsRepository.findById(savedBasket.getId());

        assertTrue(foundBBasket.isPresent());
        assertEquals("test", foundBBasket.get().getProductName());
    }

    @Test
    public void findAll() {
        Baskets basket=new Baskets();
        basket.setProductName("test");
        basket.setQuantity(1L);
        basket.setProductStatus("T");
        basket.setProducts(null);
        basket.setUsers(null);
        basket.setCreateDate("20231125");
        basket.setUpdateDate("20231125");

        Baskets savedBasket = basketsRepository.save(basket);

        List<Baskets> baskets  = basketsRepository.findAll();
        assertFalse(baskets.isEmpty());
    }

    @Test
    public void findById() {
        Baskets basket=new Baskets();
        basket.setProductName("test");
        basket.setQuantity(1L);
        basket.setProductStatus("T");
        basket.setProducts(null);
        basket.setUsers(null);
        basket.setCreateDate("20231125");
        basket.setUpdateDate("20231125");

        Baskets savedBasket = basketsRepository.save(basket);

        Optional<Baskets> foundBasket= basketsRepository.findById(savedBasket.getId());
        assertTrue(foundBasket.isPresent());
        assertEquals("test", foundBasket.get().getProductName());
    }

    @Test
    public void update() {
        Baskets basket=new Baskets();
        basket.setProductName("test");
        basket.setQuantity(1L);
        basket.setProductStatus("T");
        basket.setProducts(null);
        basket.setUsers(null);
        basket.setCreateDate("20231125");
        basket.setUpdateDate("20231125");

        Baskets savedBasket = basketsRepository.save(basket);

        Baskets basketToUpdate = basketsRepository.findById(savedBasket.getId()).get();
        basketToUpdate.setProductName("update test");
        basketsRepository.save(basketToUpdate);

        Optional<Baskets> updatedBaskets = basketsRepository.findById(savedBasket.getId());
        assertTrue(updatedBaskets.isPresent());
        assertEquals("update test", updatedBaskets.get().getProductName());
    }

    @Test
    public void deleteAll() {
        Baskets basket=new Baskets();
        basket.setProductName("test");
        basket.setQuantity(1L);
        basket.setProductStatus("T");
        basket.setProducts(null);
        basket.setUsers(null);
        basket.setCreateDate("20231125");
        basket.setUpdateDate("20231125");

        Baskets savedBasket = basketsRepository.save(basket);
        basketsRepository.deleteAll();
        List<Baskets> beginBaskets = basketsRepository.findAll();
        assertTrue(beginBaskets.isEmpty());
    }
}