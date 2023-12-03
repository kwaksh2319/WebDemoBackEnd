package kr.co.kshproject.webDemo.Applicaiton.BeginOrder;

import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BeginOrderService {
    BeginOrder save(BeginOrderDTO beginOrderDTO) ;
    List<BeginOrder> findAll();
    Page<BeginOrder> findAll(int page, int size);
    Optional<BeginOrder> findById(Long id);
    BeginOrder update(Long id, BeginOrderDTO beginOrderDTO);
    void deleteById(Long id);
    void deleteAll();
}
