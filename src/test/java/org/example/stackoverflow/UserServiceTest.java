package org.example.stackoverflow;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Entity.User;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.addUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
        assertEquals("John Doe", savedUser.getName());

        verify(userRepository, times(1)).save(user);
    }
}
