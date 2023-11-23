package com.neosoft.UserPOC.service;

import com.neosoft.UserPOC.entity.User;
import com.neosoft.UserPOC.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setName("TestNames");
        user.setLastName("TestLastNames");
        user.setAddress("klm");
        user.setDateOfBirth(LocalDate.now());
        user.setJoiningDate(LocalDate.now());
        user.setPinCode(448866);
        user.setMobileNum(011255);
    }

    @Test
    void save() {
        when(userRepository.save(any())).thenReturn(user);
        assertEquals("User added successfully", userService.save(user));
    }

    @Test
    void getUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        User userById = userService.getUserById(1L);
        assertEquals(user, userById);

    }

    @Test
    void findByNameOrLastNameOrPinCode() {
        when(userRepository.findByNameOrLastNameOrPinCode(any(), any(), any())).thenReturn(List.of(user));
        assertEquals(List.of(user), userService.findByNameOrLastNameOrPinCode(Optional.of("Name"), Optional.of("TestLastName"), Optional.of(338899)));

    }

    @Test
    void updateUser() {
        user.setId(1L);
        when(userRepository.save(any())).thenReturn(user);
        assertEquals("User updated successfully for ID 1", userService.updateUser(user));


    }

    @Test
    void hardDelete() {
        doNothing().when(userRepository).deleteById(anyLong());
        userService.hardDelete(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAllByOrderByDateOfBirthAscJoiningDateAsc() {
        when(userRepository.findAllByOrderByDateOfBirthAscJoiningDateAsc()).thenReturn(List.of(user));
        assertEquals(List.of(user), userService.findAllByOrderByDateOfBirthAscJoiningDateAsc());
    }

    @Test
    void softDelete() {

        when(userRepository.getById(anyLong())).thenReturn(user);
        assertEquals("User delete successfully for ID 1", userService.softDelete(1L));
    }
}