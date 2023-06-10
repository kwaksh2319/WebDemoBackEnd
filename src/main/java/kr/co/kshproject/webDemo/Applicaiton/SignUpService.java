package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.Users;
import kr.co.kshproject.webDemo.Domain.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SignUpService {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(Users users) {
        String hashedPassword=passwordEncoder.encode(users.getPassword());
        System.out.println(hashedPassword);
        users.setPassword(hashedPassword);
        usersDao.save(users);
    }

}
