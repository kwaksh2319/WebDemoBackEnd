package kr.co.kshproject.webDemo.Domain.Orders;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrdersCustomRepository {
    Optional<Orders> findById(Long id);
    List<OrdersDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
