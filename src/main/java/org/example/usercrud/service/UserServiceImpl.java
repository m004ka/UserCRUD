package org.example.usercrud.service;

import lombok.RequiredArgsConstructor;
import org.example.usercrud.dto.UserDTO;
import org.example.usercrud.model.User;
import org.example.usercrud.repositiory.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .mail(userDTO.getMail())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = getUser(id);
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setMail(userDTO.getMail());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }
}
