package kr.co.kshproject.webDemo.Applicaiton.User;

import kr.co.kshproject.webDemo.Domain.Users.Users;
import kr.co.kshproject.webDemo.Domain.Users.UsersDTO;
import kr.co.kshproject.webDemo.Domain.Users.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;
    private UsersDTO usersDTO;

    @BeforeEach
    void setUp() {
        // 초기화 코드, 테스트에 사용할 객체 생성 등
        user = new Users();
        user.setName("testNAME");
        user.setPassword("testPassword");
        user.setUsername("testUsername");
        user.setLevel(1L);
        user.setEmail("testemail");

        usersDTO = new UsersDTO();
        usersDTO.setName("updateName");
        usersDTO.setLevel(2L);
        usersDTO.setEmail("updatemail");
        usersDTO.setPassword("updatePw");
        usersDTO.setUsername("updateUserName");
    }

    @Test
    void save() {
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users savedUsers=userService.save(user);
        assertNotNull(savedUsers);
        verify(usersRepository).save(any(Users.class));
    }

    @Test
    void findAll() {
        when(usersRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<Users> users=userService.findAll();
        assertFalse(users.isEmpty());
        verify(usersRepository).findAll();
    }

    @Test
    void findAllPage() {
        Page<Users>page=new PageImpl<>(Collections.singletonList(user));
        when(usersRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<Users> users=userService.findAll();
        assertFalse(users.isEmpty());
        verify(usersRepository).findAll();
    }

    @Test
    void findById() {
        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<Users> foundUser = userService.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(usersRepository).findById(anyLong());
    }

    @Test
    void update() {
        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users updatedUser = userService.update(1L, usersDTO);
        assertNotNull(updatedUser);
        verify(usersRepository, times(2)).findById(anyLong());
        verify(usersRepository).save(any(Users.class));
    }

    @Test
    void deleteById() {
        doNothing().when(usersRepository).deleteById(anyLong());
        userService.deleteById(1L);
        verify(usersRepository).deleteById(anyLong());
    }

    @Test
    void deleteAll() {
        doNothing().when(usersRepository).deleteAll();
        userService.deleteAll();
        verify(usersRepository).deleteAll();
    }
}