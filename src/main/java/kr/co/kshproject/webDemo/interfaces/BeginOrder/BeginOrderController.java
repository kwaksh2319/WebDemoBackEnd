package kr.co.kshproject.webDemo.interfaces.BeginOrder;

import kr.co.kshproject.webDemo.Applicaiton.BeginOrder.BeginOrderService;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/beginorder")
public class BeginOrderController {
    private final BeginOrderService beginOrderService;

    @Autowired
    public BeginOrderController(BeginOrderService beginOrderService){
        this.beginOrderService=beginOrderService;
    }

    @PostMapping
    public ResponseEntity<BeginOrder> save(@RequestBody BeginOrderDTO beginOrderDTO){
        return ResponseEntity.ok(beginOrderService.save(beginOrderDTO));
    }

    @GetMapping
    public ResponseEntity<List<BeginOrder>> findAll(){
        return ResponseEntity.ok( beginOrderService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<BeginOrder>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( beginOrderService.findAll(page,size) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeginOrder> update(@PathVariable Long id, @RequestBody BeginOrderDTO beginOrderDTO){
        return ResponseEntity.ok(beginOrderService.update(id,beginOrderDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        beginOrderService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        beginOrderService.deleteAll();
    }
}
