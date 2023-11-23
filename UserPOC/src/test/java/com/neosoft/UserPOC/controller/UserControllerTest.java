package com.neosoft.UserPOC.controller;

import com.neosoft.UserPOC.entity.User;
import com.neosoft.UserPOC.service.UserService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setName("TestName");
        user.setLastName("TestLastName");
        user.setAddress("Lmn");
        user.setDateOfBirth(LocalDate.now());
        user.setJoiningDate(LocalDate.now());
        user.setPinCode(338899);
        user.setMobileNum(9901133);
    }

    @Test
    void save() {
        when(userService.save(any())).thenReturn("User added successfully");
        assertEquals("User added successfully", userController.save(user));
    }

    @Test
    void saveWhenUserNull() {
        when(userService.save(any())).thenReturn("User Not Added");
        assertEquals("User Not Added", userController.save(null));
    }

    @Test
    void getUserById() {
        when(userService.getUserById(anyLong())).thenReturn(user);
        User userById = userController.getUserById(1L);
        assertEquals(user, userById);
    }

    @Test
    void getUserByIdEmpty() {
        when(userService.getUserById(anyLong())).thenReturn(new User());
        User userById = userController.getUserById(1L);
        assertEquals(new User(), userById);
    }

    @Test
    void findByNameOrLastNameOrPinCode() {
        when(userService.findByNameOrLastNameOrPinCode(any(), any(), any())).thenReturn(List.of(user));
        assertEquals(List.of(user), userController.findByNameOrLastNameOrPinCode(Optional.of("Name"), Optional.of("TestLastName"), Optional.of(338899)));
    }

    @Test
    void update() {
        user.setId(1L);
        when(userService.updateUser(any())).thenReturn("User updated successfully for ID 1");
        assertEquals("User updated successfully for ID 1", userController.update(user));

    }

    @Test
    void hardDelete() {
        when(userService.hardDelete(anyLong())).thenReturn("User Delete successfully for ID 1");
        assertEquals("User Delete successfully for ID 1", userController.hardDelete(1L));
    }

    @Test
    void softDelete() {
        when(userService.softDelete(anyLong())).thenReturn("User Delete successfully for ID 1");
        assertEquals("User Delete successfully for ID 1", userController.softDelete(1L));
    }

    @Test
    void findAllByOrderByDateOfBirthAscJoiningDateAsc() {
        when(userService.findAllByOrderByDateOfBirthAscJoiningDateAsc()).thenReturn(List.of(user));
        assertEquals(List.of(user), userController.findAllByOrderByDateOfBirthAscJoiningDateAsc());


    }
}