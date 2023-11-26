package kr.co.kshproject.webDemo.Applicaiton.Basket;

import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BasketService {
    Baskets save(Baskets baskets);
    List<Baskets> findAll();
    Page<Baskets> findAll(int page, int size);
    Optional<Baskets> findById(Long id);
    Baskets update(Long id, BasketsDTO basketsDTO);
    void deleteById(Long id);
    void deleteAll();
}
