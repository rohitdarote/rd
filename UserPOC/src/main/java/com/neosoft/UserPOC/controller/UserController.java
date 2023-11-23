package com.neosoft.UserPOC.controller;

import com.neosoft.UserPOC.entity.User;
import com.neosoft.UserPOC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/get/{Id}")
    public User getUserById(@PathVariable Long Id) {
        return userService.getUserById(Id);
    }

    @GetMapping("/getAll")
    public List<User> findAllByOrderByDateOfBirthAscJoiningDateAsc() {
        return userService.findAllByOrderByDateOfBirthAscJoiningDateAsc();
    }

    @GetMapping("/search")
    public List<User> findByNameOrLastNameOrPinCode(@RequestParam(name = "name") Optional<String> name, @RequestParam(name = "lastName") Optional<String> lastName, @RequestParam(name = "pinCode") Optional<Integer> pinCode) {
        return userService.findByNameOrLastNameOrPinCode(name, lastName, pinCode);
    }

    @DeleteMapping("/hardDelete/{id}")
    public String hardDelete(@PathVariable Long id) {
        return userService.hardDelete(id);
    }

    @DeleteMapping("/softDelete/{id}")
    public String softDelete(@PathVariable Long id) {
        return userService.softDelete(id);
    }


}
