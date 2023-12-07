package kr.co.kshproject.webDemo.Applicaiton.Basket;

import kr.co.kshproject.webDemo.Applicaiton.Product.ProductService;
import kr.co.kshproject.webDemo.Applicaiton.User.UserService;
import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsCustomRepository;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsDTO;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsRepository;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import kr.co.kshproject.webDemo.Domain.Users.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BasketServiceImpl implements BasketService{
    private final BasketsRepository basketsRepository;

    private final BasketsCustomRepository basketsCustomRepository;

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public BasketServiceImpl(BasketsRepository basketsRepository,
                             BasketsCustomRepository basketsCustomRepository,
                             ProductService productService,
                             UserService userService
    ){
        this.basketsRepository=basketsRepository;
        this.basketsCustomRepository=basketsCustomRepository;
        this.productService=productService;
        this.userService=userService;
    }

    @Override
    public Baskets save(BasketsDTO basketsDTO) {
        Baskets baskets=new Baskets(basketsDTO);
        return basketsRepository.save(baskets);
    }

    @Override
    public List<BasketsDTO> findAll() {
        return basketsCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        return basketsCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Baskets> findById(Long id) {
        return basketsCustomRepository.findById(id);
    }

    @Override
    public Baskets update(Long id, BasketsDTO basketsDTO) {
        return basketsRepository.save(ConverEntity(id,basketsDTO));
    }

    @Override
    public void deleteById(Long id) {
        basketsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        basketsRepository.deleteAll();
    }

    private Baskets ConverEntity(Long id, BasketsDTO basketsDTO){
        Optional<Baskets> basket=basketsRepository.findById(id);
        Optional<Products> product=productService.findById(basketsDTO.getProductId());
        Optional<Users>user=userService.findById(basketsDTO.getUserId());
        basket.get().setProductName(basketsDTO.getProductName());
        basket.get().setQuantity(basketsDTO.getQuantity());
        basket.get().setProductStatus(basketsDTO.getProductStatus());
        basket.get().setCreateDate(basketsDTO.getCreateDate());
        basket.get().setUpdateDate(basketsDTO.getUpdateDate());
        if(product.isPresent()==true){
            basket.get().setProducts(product.get());
        }
        if(user.isPresent()==true){
            basket.get().setUsers(user.get());
        }
        return basket.get();
    }
}
