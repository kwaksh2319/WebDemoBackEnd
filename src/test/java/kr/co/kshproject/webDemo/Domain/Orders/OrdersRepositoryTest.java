package kr.co.kshproject.webDemo.Domain.Orders;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @BeforeEach
    void before(){

    }

    @AfterEach
    void after() {
        // 테스트 데이터 정리
        ordersRepository.deleteAll();
    }

    @Test
    public void save() {
        Orders order=new Orders();
        order.setStatus("T");
        order.setPrice(100L);
        order.setPayment("card");
        order.setCancel(false);
        order.setCreatedDate("20231125");
        order.setUpdateDate("20231125");
        order.setUsers(null);
        Orders savedOrder = ordersRepository.save(order);
        Optional<Orders> foundOrder = ordersRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals("card", foundOrder.get().getPayment());
    }

    @Test
    public void findAll() {
        Orders order=new Orders();
        order.setStatus("T");
        order.setPrice(100L);
        order.setPayment("card");
        order.setCancel(false);
        order.setCreatedDate("20231125");
        order.setUpdateDate("20231125");
        order.setUsers(null);
        Orders savedOrders = ordersRepository.save(order);

        List<Orders> Orders  = ordersRepository.findAll();
        assertFalse(Orders.isEmpty());
    }

    @Test
    public void findById() {
        Orders order=new Orders();
        order.setStatus("T");
        order.setPrice(100L);
        order.setPayment("card");
        order.setCancel(false);
        order.setCreatedDate("20231125");
        order.setUpdateDate("20231125");
        order.setUsers(null);
        Orders savedOrder = ordersRepository.save(order);

        Optional<Orders> foundOrder = ordersRepository.findById(savedOrder.getId());
        assertTrue(foundOrder.isPresent());
        assertEquals("card", foundOrder.get().getPayment());
    }

    @Test
    public void update() {
        Orders order=new Orders();
        order.setStatus("T");
        order.setPrice(100L);
        order.setPayment("card");
        order.setCancel(false);
        order.setCreatedDate("20231125");
        order.setUpdateDate("20231125");
        order.setUsers(null);
        Orders savedOrder = ordersRepository.save(order);

        Orders orderToUpdate = ordersRepository.findById(savedOrder.getId()).get();
        orderToUpdate.setPayment("cash");
        ordersRepository.save(orderToUpdate);

        Optional<Orders> updatedOrder = ordersRepository.findById(savedOrder.getId());
        assertTrue(updatedOrder.isPresent());
        assertEquals("cash", updatedOrder.get().getPayment());
    }

    @Test
    public void deleteAll() {
        Orders order=new Orders();
        order.setStatus("T");
        order.setPrice(100L);
        order.setPayment("card");
        order.setCancel(false);
        order.setCreatedDate("20231125");
        order.setUpdateDate("20231125");
        order.setUsers(null);
        Orders orderProduct = ordersRepository.save(order);

        ordersRepository.deleteAll();
        List<Orders> orders = ordersRepository.findAll();
        assertTrue(orders.isEmpty());
    }
}