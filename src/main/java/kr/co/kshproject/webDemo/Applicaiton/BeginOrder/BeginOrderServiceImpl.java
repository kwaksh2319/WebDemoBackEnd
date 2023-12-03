package kr.co.kshproject.webDemo.Applicaiton.BeginOrder;

import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BeginOrderServiceImpl implements BeginOrderService{
    private final BeginOrderRepository beginOrderRepository;

    @Autowired
    public BeginOrderServiceImpl(BeginOrderRepository beginOrderRepository){
        this.beginOrderRepository=beginOrderRepository;
    }

    @Override
    public BeginOrder save(BeginOrderDTO beginOrderDTO) {
        BeginOrder beginOrder=new BeginOrder(beginOrderDTO);
        return beginOrderRepository.save(beginOrder);
    }

    @Override
    public List<BeginOrder> findAll() {
        return beginOrderRepository.findAll();
    }

    @Override
    public Page<BeginOrder> findAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return beginOrderRepository.findAll(pageable);
    }

    @Override
    public Optional<BeginOrder> findById(Long id) {
        return beginOrderRepository.findById(id);
    }

    @Override
    public BeginOrder update(Long id, BeginOrderDTO beginOrderDTO) {
        return beginOrderRepository.save(ConverEntity(id,beginOrderDTO));
    }

    @Override
    public void deleteById(Long id) {
        beginOrderRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        beginOrderRepository.deleteAll();
    }

    private BeginOrder ConverEntity(Long id, BeginOrderDTO beginOrderDTO){
        Optional<BeginOrder> beginOrder=beginOrderRepository.findById(id);
        beginOrder.get().setOrders(beginOrderDTO.getOrders());
        beginOrder.get().setPicture(beginOrderDTO.getPicture());
        beginOrder.get().setProducts(beginOrderDTO.getProducts());
        beginOrder.get().setQuantity(beginOrderDTO.getQuantity());
        beginOrder.get().setCreatedDate(beginOrderDTO.getCreatedDate());
        beginOrder.get().setUpdateDate(beginOrderDTO.getUpdateDate());

        return beginOrder.get();
    }
}
