package com.neosoft.UserPOC.service;

import com.neosoft.UserPOC.entity.User;
import com.neosoft.UserPOC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public String save(User user) {
        return userRepository.save(user) != null ? "User added successfully" : "User not added";
    }

    public User getUserById(Long Id) {
        return userRepository.findById(Id).orElse(new User());
    }

    public List<User> findByNameOrLastNameOrPinCode(Optional<String> name, Optional<String> lastName, Optional<Integer> pinCode) {
        return userRepository.findByNameOrLastNameOrPinCode(name, lastName, pinCode).stream().filter(User::isActive).collect(Collectors.toList());
    }

    public String updateUser(User user) {
        return userRepository.save(user) != null ? String.format("User updated successfully for ID %d", user.getId()) : String.format("User Not updated forID %d", user.getId());
    }

    public String hardDelete(Long id) {
        userRepository.deleteById(id);
        return String.format("User Delete successfully for ID%d", id);
    }

    public List<User> findAllByOrderByDateOfBirthAscJoiningDateAsc() {
        return userRepository.findAllByOrderByDateOfBirthAscJoiningDateAsc().stream().filter(User::isActive).collect(Collectors.toList());
    }

    public String softDelete(Long id) {
        User user = userRepository.getById(id);
        user.setActive(false);
        userRepository.save(user);
        return String.format("User delete successfully for ID %d", id);
    }
}



