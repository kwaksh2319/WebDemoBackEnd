package kr.co.kshproject.webDemo.Applicaiton.User;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersCustomRepository;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import kr.co.kshproject.webDemo.Domain.Users.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements  UserService {
    private final int MAX_PAGE_SIZE = 1000;
    private final int MAX_ID = 10000000;
    private final UsersRepository usersRepository;
    private final UsersCustomRepository usersCustomRepository;

   // private final PasswordEncoder passwordEncoder;

    private List<String> roles;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, UsersCustomRepository usersCustomRepository){
        this.usersRepository=usersRepository;
        this.usersCustomRepository=usersCustomRepository;
      //  this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Users save(UsersDTO usersDTO) {
        Users user= new Users(usersDTO);

        return usersRepository.save(user);
    }

    @Override
    public List<UsersDTO> findAll() {
       return usersCustomRepository.findAll();
    }

    @Override
    public Map<String,List> findAll(int page, int size) {
        if (page < 0  || size <= 0 || size > MAX_PAGE_SIZE || page > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("페이지 또는 사이즈 값이 유효하지 않습니다.");
        }
        return usersCustomRepository.findAll(page,size);
    }

    @Override
    public Optional<Users> findById(Long id) {
        if (id < 0 || id > MAX_ID ) {
            throw new IllegalArgumentException("id 값이 유효하지 않습니다.");
        }
        return usersCustomRepository.findById(id);
    }

    @Override
    public void update(Long id,UsersDTO usersDTO) {
         usersRepository.save(convertEntity(id,usersDTO));
    }

    @Override
    public void deleteById(Long id) {
        if (id < 0 || id > MAX_ID ) {
            throw new IllegalArgumentException("id 값이 유효하지 않습니다.");
        }
        usersRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        usersRepository.deleteAll();
    }



    private Users convertEntity(Long id,UsersDTO usersDTO){
        Optional<Users> user=usersCustomRepository.findById(id);

        user.get().setUsername(usersDTO.getUsername());
        user.get().setEmail(usersDTO.getEmail());
        user.get().setName(usersDTO.getName());
        user.get().setLevel(usersDTO.getLevel());
        user.get().setPassword(usersDTO.getPassword());

        return user.get();
    }


}
