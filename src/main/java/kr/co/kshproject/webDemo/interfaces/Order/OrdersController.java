package kr.co.kshproject.webDemo.interfaces.Order;


import kr.co.kshproject.webDemo.Applicaiton.Order.OrdersService;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
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
    public ResponseEntity<Orders> save(@Validated @RequestBody OrdersDTO ordersDTO){
        return ResponseEntity.ok(ordersService.save(ordersDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> findAll(){
        return ResponseEntity.ok( ordersService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@Min(1) @PathVariable int page, @Range(min=1,max=100) @PathVariable int size){
        return ResponseEntity.ok( ordersService.findAll(page,size) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Orders>> findById(@Min(1) @PathVariable Long id){
        return ResponseEntity.ok(ordersService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> update(@Min(1) @PathVariable Long id, @Validated @RequestBody OrdersDTO ordersDTO){
        return ResponseEntity.ok(ordersService.update(id,ordersDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
        ordersService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        ordersService.deleteAll();
    }

}
