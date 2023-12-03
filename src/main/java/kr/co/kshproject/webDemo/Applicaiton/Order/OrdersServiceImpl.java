package kr.co.kshproject.webDemo.Applicaiton.Order;

import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository){
        this.ordersRepository=ordersRepository;
    }

    @Transactional
    @Override
    public Orders save(OrdersDTO ordersDTO) {
        Orders order=new Orders(ordersDTO);

        return ordersRepository.save(order);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Page<Orders> findAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return ordersRepository.findAll(pageable);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }


    @Transactional
    @Override
    public Orders update(Long id, OrdersDTO ordersDTO) {
        return ordersRepository.save(ConverEntity(id,ordersDTO));
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void deleteAll() {
        ordersRepository.deleteAll();
    }

    private Orders ConverEntity(Long id, OrdersDTO ordersDTO){
        Optional<Orders> order=ordersRepository.findById(id);
        order.get().setPayment(ordersDTO.getPayment());
        order.get().setPrice(ordersDTO.getPrice());
        order.get().setCancel(ordersDTO.getCancel());
        order.get().setStatus(ordersDTO.getStatus());
        order.get().setUsers(ordersDTO.getUsers());
        order.get().setCreatedDate(ordersDTO.getCreatedDate());
        order.get().setUpdateDate(ordersDTO.getUpdateDate());

        return order.get();
    }
}
