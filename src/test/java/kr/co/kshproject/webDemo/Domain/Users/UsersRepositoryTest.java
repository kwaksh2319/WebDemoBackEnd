package kr.co.kshproject.webDemo.Domain.Users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void save() {
        Users newUser = new Users();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setName("Test User");
        newUser.setLevel(1L);
        newUser.setPassword("password");
        Users savedUser = usersRepository.save(newUser);
        Optional<Users> foundUser = usersRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    public void findAll() {
        Users newUser = new Users();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setName("Test User");
        newUser.setLevel(1L);
        newUser.setPassword("password");
        Users savedUser = usersRepository.save(newUser);

        List<Users> users = usersRepository.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void findById() {
        Users newUser = new Users();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setName("Test User");
        newUser.setLevel(1L);
        newUser.setPassword("password");
        Users savedUser = usersRepository.save(newUser);

        Optional<Users> foundUser = usersRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    public void update() {
        Users newUser = new Users();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setName("Test User");
        newUser.setLevel(1L);
        newUser.setPassword("password");
        Users savedUser = usersRepository.save(newUser);

        Users userToUpdate = usersRepository.findById(savedUser.getId()).get();
        userToUpdate.setName("Updated Name");
        usersRepository.save(userToUpdate);

        Optional<Users> updatedUser = usersRepository.findById(savedUser.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("Updated Name", updatedUser.get().getName());
    }

    @Test
    public void deleteAll() {
        Users newUser = new Users();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setName("Test User");
        newUser.setLevel(1L);
        newUser.setPassword("password");
        Users savedUser = usersRepository.save(newUser);

        usersRepository.deleteAll();
        List<Users> users = usersRepository.findAll();
        assertTrue(users.isEmpty());
    }

}