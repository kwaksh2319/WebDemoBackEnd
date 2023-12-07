package kr.co.kshproject.webDemo.Applicaiton.User;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Users save(UsersDTO usersDTO);
    List<UsersDTO> findAll();

    Map<String,List> findAll(int page, int size);
    Optional<Users> findById(Long id);
    void update(Long id, UsersDTO usersDTO);
    void deleteById(Long id);
    void deleteAll();

}
