package kr.co.kshproject.webDemo.Applicaiton.BeginOrder;

import kr.co.kshproject.webDemo.Applicaiton.Order.OrdersService;
import kr.co.kshproject.webDemo.Applicaiton.Product.ProductService;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrder;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderCustomRepository;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderDTO;
import kr.co.kshproject.webDemo.Domain.BeginOrder.BeginOrderRepository;
import kr.co.kshproject.webDemo.Domain.Orders.Orders;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BeginOrderServiceImpl implements BeginOrderService{
    private final BeginOrderRepository beginOrderRepository;

    private final BeginOrderCustomRepository beginOrderCustomRepository;

    private final ProductService productService;
    private final OrdersService ordersService;

    @Autowired
    public BeginOrderServiceImpl(BeginOrderRepository beginOrderRepository,
                                 BeginOrderCustomRepository beginOrderCustomRepository,
                                 ProductService productService,
                                 OrdersService ordersService
                                 ){
        this.beginOrderRepository=beginOrderRepository;
        this.beginOrderCustomRepository=beginOrderCustomRepository;
        this.productService=productService;
        this.ordersService=ordersService;
    }

    @Override
    public BeginOrder save(BeginOrderDTO beginOrderDTO) {
        BeginOrder beginOrder=new BeginOrder(beginOrderDTO);
        return beginOrderRepository.save(beginOrder);
    }

    @Override
    public List<BeginOrderDTO> findAll() {
        return beginOrderCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return beginOrderCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<BeginOrder> findById(Long id) {
        return beginOrderCustomRepository.findById(id);
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
        Optional<BeginOrder> beginOrder= beginOrderRepository.findById(id);
        Optional<Products> product = productService.findById(beginOrderDTO.getProductId());
        Optional<Orders> order = ordersService.findById(beginOrderDTO.getOrderId());

        beginOrder.get().setPicture(beginOrderDTO.getPicture());
        beginOrder.get().setQuantity(beginOrderDTO.getQuantity());
        beginOrder.get().setCreatedDate(beginOrderDTO.getCreatedDate());
        beginOrder.get().setUpdateDate(beginOrderDTO.getUpdateDate());

        if(product.isPresent()){
            beginOrder.get().setProducts(product.get());
        }
        if(order.isPresent()){
            beginOrder.get().setOrders(order.get());
        }
        return beginOrder.get();
    }
}
