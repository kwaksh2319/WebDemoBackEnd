package kr.co.kshproject.webDemo.Applicaiton.Basket;

import kr.co.kshproject.webDemo.Domain.Baskets.Baskets;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsDTO;
import kr.co.kshproject.webDemo.Domain.Baskets.BasketsRepository;
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
public class BasketServiceImpl implements BasketService{
    private final BasketsRepository basketsRepository;

    @Autowired
    public BasketServiceImpl(BasketsRepository basketsRepository){
        this.basketsRepository=basketsRepository;
    }

    @Override
    public Baskets save(BasketsDTO basketsDTO) {
        Baskets baskets=new Baskets(basketsDTO);
        return basketsRepository.save(baskets);
    }

    @Override
    public List<Baskets> findAll() {
        return basketsRepository.findAll();
    }

    @Override
    public Page<Baskets> findAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return basketsRepository.findAll(pageable);
    }

    @Override
    public Optional<Baskets> findById(Long id) {
        return basketsRepository.findById(id);
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
        basket.get().setProductName(basketsDTO.getProductName());
        basket.get().setQuantity(basketsDTO.getQuantity());
        basket.get().setProductStatus(basketsDTO.getProductStatus());
        basket.get().setProducts(basketsDTO.getProducts());
        basket.get().setCreateDate(basketsDTO.getCreateDate());
        basket.get().setUpdateDate(basketsDTO.getUpdateDate());
        basket.get().setUsers(basketsDTO.getUsers());
        return basket.get();
    }
}
