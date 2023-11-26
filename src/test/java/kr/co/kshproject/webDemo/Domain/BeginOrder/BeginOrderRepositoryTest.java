package kr.co.kshproject.webDemo.Domain.BeginOrder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeginOrderRepositoryTest {
    @Autowired
    private BeginOrderRepository beginOrderRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        beginOrderRepository.deleteAll();
    }

    @Test
    public void save() {
        BeginOrder beginOrder=new BeginOrder();
        beginOrder.setPicture("test");
        beginOrder.setOrders(null);
        beginOrder.setProducts(null);
        beginOrder.setQuantity(1L);
        beginOrder.setCreatedDate("20231125");
        beginOrder.setUpdateDate("20231125");

        BeginOrder savedBeginOrder = beginOrderRepository.save(beginOrder);
        Optional<BeginOrder> foundBeginOrder= beginOrderRepository.findById(savedBeginOrder.getId());

        assertTrue(foundBeginOrder.isPresent());
        assertEquals("test", foundBeginOrder.get().getPicture());
    }

    @Test
    public void findAll() {
        BeginOrder beginOrder=new BeginOrder();
        beginOrder.setPicture("test");
        beginOrder.setOrders(null);
        beginOrder.setProducts(null);
        beginOrder.setQuantity(1L);
        beginOrder.setCreatedDate("20231125");
        beginOrder.setUpdateDate("20231125");

        BeginOrder savedBeginOrder = beginOrderRepository.save(beginOrder);

        List<BeginOrder> beginOrders  = beginOrderRepository.findAll();
        assertFalse(beginOrders.isEmpty());
    }

    @Test
    public void findById() {
        BeginOrder beginOrder=new BeginOrder();
        beginOrder.setPicture("test");
        beginOrder.setOrders(null);
        beginOrder.setProducts(null);
        beginOrder.setQuantity(1L);
        beginOrder.setCreatedDate("20231125");
        beginOrder.setUpdateDate("20231125");

        BeginOrder savedBeginOrder = beginOrderRepository.save(beginOrder);

        Optional<BeginOrder> foundBeginOrder= beginOrderRepository.findById(savedBeginOrder.getId());
        assertTrue(foundBeginOrder.isPresent());
        assertEquals("test", foundBeginOrder.get().getPicture());
    }

    @Test
    public void update() {
        BeginOrder beginOrder=new BeginOrder();
        beginOrder.setPicture("test");
        beginOrder.setOrders(null);
        beginOrder.setProducts(null);
        beginOrder.setQuantity(1L);
        beginOrder.setCreatedDate("20231125");
        beginOrder.setUpdateDate("20231125");

        BeginOrder savedBeginOrder = beginOrderRepository.save(beginOrder);

        BeginOrder beginOrderToUpdate = beginOrderRepository.findById(savedBeginOrder.getId()).get();
        beginOrderToUpdate.setPicture("update test");
        beginOrderRepository.save(beginOrderToUpdate);

        Optional<BeginOrder> updatedBeginOrder = beginOrderRepository.findById(savedBeginOrder.getId());
        assertTrue(updatedBeginOrder.isPresent());
        assertEquals("update test", updatedBeginOrder.get().getPicture());
    }

    @Test
    public void deleteAll() {
        BeginOrder beginOrder=new BeginOrder();
        beginOrder.setPicture("test");
        beginOrder.setOrders(null);
        beginOrder.setProducts(null);
        beginOrder.setQuantity(1L);
        beginOrder.setCreatedDate("20231125");
        beginOrder.setUpdateDate("20231125");

        BeginOrder savedBeginOrder = beginOrderRepository.save(beginOrder);
        beginOrderRepository.deleteAll();
        List<BeginOrder> beginOrderes = beginOrderRepository.findAll();
        assertTrue(beginOrderes.isEmpty());
    }
}