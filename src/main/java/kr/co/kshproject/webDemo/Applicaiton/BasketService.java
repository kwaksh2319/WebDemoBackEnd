package kr.co.kshproject.webDemo.Applicaiton;


import kr.co.kshproject.webDemo.Domain.Baskets;
import kr.co.kshproject.webDemo.Domain.BasketsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    BasketsDao basketsDao;

    public void save(Baskets baskets) {
        basketsDao.save(baskets);
    }

    public List<Baskets> findAll(String userName, int page, int size) {
        return basketsDao.findAll(userName,page,size);
    }

    public List<Baskets> findAll(String userName) {
        return basketsDao.findAll(userName);
    }

    public void update(String bindNumber,String status) {
        basketsDao.update(bindNumber,status);
    }
}
