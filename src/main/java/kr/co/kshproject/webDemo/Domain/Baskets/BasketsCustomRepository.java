package kr.co.kshproject.webDemo.Domain.Baskets;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BasketsCustomRepository {
    Optional<Baskets> findById(Long id);
    List<BasketsDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
