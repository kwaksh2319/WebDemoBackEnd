package kr.co.kshproject.webDemo.Applicaiton.BeginOrder;

import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BeginOrderService {
    BeginOrder save(BeginOrderDTO beginOrderDTO) ;
    List<BeginOrderDTO> findAll();
    Map<String,List> findAll(int page, int size);
    Optional<BeginOrder> findById(Long id);
    BeginOrder update(Long id, BeginOrderDTO beginOrderDTO);
    void deleteById(Long id);
    void deleteAll();
}
