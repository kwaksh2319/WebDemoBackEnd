package kr.co.kshproject.webDemo.Applicaiton;


import kr.co.kshproject.webDemo.Domain.Baskets;
import kr.co.kshproject.webDemo.Domain.BasketsDao;
import kr.co.kshproject.webDemo.Domain.BasketsWithProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    BasketsDao basketsDao;

    public void save(Baskets baskets, HttpSession session) {
        //유저
        String userId="";
        Object attribute = session.getAttribute("user");
        if (attribute instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) attribute;
            // notice.setUsername(user.getUsername());
            userId=user.getUsername();
        }
        baskets.getBasketId().setUsersId(userId);

        //날짜
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        baskets.getBasketId().setDate(formattedDateTime);

        //bindNumber
        baskets.setBindNumber(userId+baskets.getBindNumber());
        basketsDao.save(baskets);
    }

    public List<BasketsWithProduct> findAll(String userName, String status, int page, int size) {
        return basketsDao.findAll(userName,status,page,size);
    }

    public List<Baskets> findAll(String userName) {
        return basketsDao.findAll(userName);
    }

    public void update(String bindNumber,String status) {
        basketsDao.update(bindNumber,status);
    }
}
