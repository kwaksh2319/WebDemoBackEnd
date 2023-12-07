package kr.co.kshproject.webDemo.interfaces.BeginOrder;

import kr.co.kshproject.webDemo.Applicaiton.BeginOrder.BeginOrderService;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<List<BeginOrderDTO>> findAll(){
        return ResponseEntity.ok( beginOrderService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( beginOrderService.findAll(page,size) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BeginOrder>> findById(@PathVariable Long id){
        return ResponseEntity.ok( beginOrderService.findById(id) );
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
