package kr.co.kshproject.webDemo.interfaces.Basket;

import kr.co.kshproject.webDemo.Applicaiton.Basket.BasketService;
import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Baskets> save(BasketsDTO basketsDTO){
        return ResponseEntity.ok(basketService.save(basketsDTO));
    }

    @GetMapping
    public ResponseEntity<List<Baskets>> findAll(){
        return ResponseEntity.ok( basketService.findAll() );
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<Baskets>> findAll(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok( basketService.findAll(page,size) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Baskets> update(@PathVariable Long id, @RequestBody BasketsDTO basketsDTO ){
        return ResponseEntity.ok(basketService.update(id,basketsDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        basketService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        basketService.deleteAll();
    }
}
