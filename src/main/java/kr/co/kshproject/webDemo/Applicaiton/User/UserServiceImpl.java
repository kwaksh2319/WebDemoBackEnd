package kr.co.kshproject.webDemo.Applicaiton.User;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import kr.co.kshproject.webDemo.Domain.Users.UsersRepository;
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
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Page<Users> findAll(int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
        return usersRepository.findAll(pageable);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users update(Long id,UsersDTO usersDTO) {
        Optional<Users> user=usersRepository.findById(id);
        return usersRepository.save(ConverEntity(id,usersDTO));
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        usersRepository.deleteAll();
    }

    private Users ConverEntity(Long id,UsersDTO usersDTO){
        Optional<Users> user=usersRepository.findById(id);
        user.get().setName(usersDTO.getName());
        user.get().setEmail(usersDTO.getEmail());
        user.get().setLevel(usersDTO.getLevel());
        user.get().setUsername(usersDTO.getUsername());
        user.get().setPassword(usersDTO.getPassword());
        return user.get();
    }
}
