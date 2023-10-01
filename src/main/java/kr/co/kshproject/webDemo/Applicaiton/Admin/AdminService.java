package kr.co.kshproject.webDemo.Applicaiton.Admin;

import kr.co.kshproject.webDemo.Domain.ProductDao;
import kr.co.kshproject.webDemo.Domain.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

;

@Service
@Transactional
public class AdminService implements AdminServiceInterface{

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UsersDao usersDao;

}
