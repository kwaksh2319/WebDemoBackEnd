package kr.co.kshproject.webDemo.Applicaiton.Order;

import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    Orders save(OrdersDTO ordersDTO);
    List<Orders> findAll();
    Page<Orders> findAll(int page, int size);
    Optional<Orders> findById(Long id);
    Orders update(Long id, OrdersDTO ordersDTO);
    void deleteById(Long id);
    void deleteAll();
}
