package kr.co.kshproject.webDemo.interfaces.Order;


import kr.co.kshproject.webDemo.Applicaiton.Order.OrdersService;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService){
        this.ordersService=ordersService;
    }

    @PostMapping
    public ResponseEntity<Orders> save(@RequestBody OrdersDTO ordersDTO){
        return ResponseEntity.ok(ordersService.save(ordersDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> findAll(){
        return ResponseEntity.ok( ordersService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( ordersService.findAll(page,size) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Orders>> findById(@PathVariable Long id){
        return ResponseEntity.ok(ordersService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> update(@PathVariable Long id, @RequestBody OrdersDTO ordersDTO){
        return ResponseEntity.ok(ordersService.update(id,ordersDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        ordersService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        ordersService.deleteAll();
    }

}
