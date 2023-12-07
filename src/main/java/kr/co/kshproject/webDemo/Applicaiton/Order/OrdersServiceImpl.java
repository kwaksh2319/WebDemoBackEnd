package kr.co.kshproject.webDemo.Applicaiton.Order;

import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersCustomRepository;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersDTO;
import kr.co.kshproject.webDemo.Domain.Orders.OrdersRepository;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersCustomRepository ordersCustomRepository;
    private final UserService userService;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             OrdersCustomRepository ordersCustomRepository,
                             UserService userService){
        this.ordersRepository=ordersRepository;
        this.ordersCustomRepository=ordersCustomRepository;
        this.userService=userService;
    }

    @Override
    public Orders save(OrdersDTO ordersDTO) {
        Orders order=new Orders(ordersDTO);

        return ordersRepository.save(order);
    }

    @Override
    public List<OrdersDTO> findAll() {
        return ordersCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return ordersCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersCustomRepository.findById(id);
    }

    @Override
    public Orders update(Long id, OrdersDTO ordersDTO) {
        return ordersRepository.save(ConverEntity(id,ordersDTO));
    }

    @Override
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        ordersRepository.deleteAll();
    }

    private Orders ConverEntity(Long id, OrdersDTO ordersDTO){
        Optional<Orders> order=ordersRepository.findById(id);
        Long userId=ordersDTO.getUserId();
        Optional<Users> updateUser=userService.findById(userId);
        order.get().setPayment(ordersDTO.getPayment());
        order.get().setPrice(ordersDTO.getPrice());
        order.get().setCancel(ordersDTO.getCancel());
        order.get().setStatus(ordersDTO.getStatus());
        order.get().setCreatedDate(ordersDTO.getCreatedDate());
        order.get().setUpdateDate(ordersDTO.getUpdateDate());
        if(updateUser.isPresent()==true){
            order.get().setUsers(updateUser.get());
        }

        return order.get();
    }
}
