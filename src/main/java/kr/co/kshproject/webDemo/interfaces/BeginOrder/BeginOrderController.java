package kr.co.kshproject.webDemo.interfaces.BeginOrder;

import kr.co.kshproject.webDemo.Applicaiton.BeginOrder.BeginOrderService;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
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
@RequestMapping("/beginorder")
public class BeginOrderController {
    private final BeginOrderService beginOrderService;

    @Autowired
    public BeginOrderController(BeginOrderService beginOrderService){
        this.beginOrderService=beginOrderService;
    }

    @PostMapping
    public ResponseEntity<BeginOrder> save(@Validated @RequestBody BeginOrderDTO beginOrderDTO){
        return ResponseEntity.ok(beginOrderService.save(beginOrderDTO));
    }

    @GetMapping
    public ResponseEntity<List<BeginOrderDTO>> findAll(){
        return ResponseEntity.ok( beginOrderService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@Min(1) @PathVariable int page, @Range(min=1,max=100) @PathVariable int size){
        return ResponseEntity.ok( beginOrderService.findAll(page,size) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BeginOrder>> findById(@Min(1) @PathVariable Long id){
        return ResponseEntity.ok( beginOrderService.findById(id) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeginOrder> update(@Min(1) @PathVariable Long id,@Validated @RequestBody BeginOrderDTO beginOrderDTO){
        return ResponseEntity.ok(beginOrderService.update(id,beginOrderDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
        beginOrderService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        beginOrderService.deleteAll();
    }
}
