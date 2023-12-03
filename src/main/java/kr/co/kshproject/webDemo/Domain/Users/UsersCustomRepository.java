package kr.co.kshproject.webDemo.Domain.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UsersCustomRepository {
     Optional<Users> findById(Long id);
     List<UsersDTO> findAll();
     Map<String,List> findAll(int page, int size);
}
