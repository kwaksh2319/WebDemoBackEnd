package kr.co.kshproject.webDemo.Applicaiton.Order;

import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrdersService {
    Orders save(OrdersDTO ordersDTO);
    List<OrdersDTO> findAll();
    Map<String,List> findAll(int page, int size);
    Optional<Orders> findById(Long id);
    Orders update(Long id, OrdersDTO ordersDTO);
    void deleteById(Long id);
    void deleteAll();
}
