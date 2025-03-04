package org.example.usercrud.controller;

import lombok.RequiredArgsConstructor;
import org.example.usercrud.dto.UserDTO;
import org.example.usercrud.model.User;
import org.example.usercrud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        User user = userService.getUser(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        userDTO.setMail(user.getMail());

        model.addAttribute("user", user);
        model.addAttribute("userDTO", userDTO);
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @ModelAttribute UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
