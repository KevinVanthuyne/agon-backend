package com.kevinvanthuyne.scored_backend.api.v1;

import com.kevinvanthuyne.scored_backend.dto.UserDto;
import com.kevinvanthuyne.scored_backend.model.User;
import com.kevinvanthuyne.scored_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Updates the given user or adds a new user if there is none yet.
     */
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userService.getUser(userDto.id()).orElse(new User());
        user.setName(userDto.name());
        user.setInitials(userDto.initials());
        User savedUser = userService.addUser(user);

        return ResponseEntity.ok(new UserDto(savedUser));
    }
}
