package kr.co.kshproject.webDemo.Domain.BeginOrder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BeginOrderCustomRepository {
    Optional<BeginOrder> findById(Long id);
    List<BeginOrderDTO> findAll();
    Map<String,List> findAll(int page, int size);
}
