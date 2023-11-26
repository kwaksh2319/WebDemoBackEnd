package kr.co.kshproject.webDemo.Applicaiton.User;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Users save(Users user);
    List<Users> findAll();

    Page<Users> findAll(int page, int size);
    Optional<Users> findById(Long id);
    Users update(Long id, UsersDTO usersDTO);
    void deleteById(Long id);
    void deleteAll();

}
