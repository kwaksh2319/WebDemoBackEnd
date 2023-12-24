package kr.co.kshproject.webDemo.interfaces.Basket;

import kr.co.kshproject.webDemo.Applicaiton.Basket.BasketService;
import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsDTO;
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
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService){
        this.basketService=basketService;
    }

    @PostMapping
    public ResponseEntity<Baskets> save(@Validated @RequestBody BasketsDTO basketsDTO){
        return ResponseEntity.ok(basketService.save(basketsDTO));
    }

    @GetMapping
    public ResponseEntity<List<BasketsDTO>> findAll(){
        return ResponseEntity.ok( basketService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Map<String,List>> findAll(@Min(1) @PathVariable int page,@Range(min=1,max=100) @PathVariable int size){
        return ResponseEntity.ok( basketService.findAll(page,size) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Baskets>> findById(@Min(1) @PathVariable Long id ){
        return ResponseEntity.ok(basketService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Baskets> update(@Min(1) @PathVariable Long id, @Validated @RequestBody BasketsDTO basketsDTO ){
        return ResponseEntity.ok(basketService.update(id,basketsDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
        basketService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        basketService.deleteAll();
    }
}
