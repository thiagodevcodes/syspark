package com.park.syspark.rest.controller;


import com.park.syspark.rest.dto.UserDto;
import com.park.syspark.rest.form.UserLoginForm;
import com.park.syspark.rest.form.UserUpdateForm;
import com.park.syspark.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtoList = userService.getAllUserDto();
        return ResponseEntity.ok().body(userDtoList);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(
            @PathVariable("email") String email
    ) {
        UserDto userDto = userService.getUserDtoByDescription(email);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(
            @Valid @RequestBody UserLoginForm userForm
    ) {
        UserDto userDto = userService.login(userForm);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> insertUser(
            @Valid @RequestBody UserLoginForm userForm
    ) {
        UserDto userDto = userService.insertUser(userForm);
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("email") String email,
            @Valid @RequestBody UserUpdateForm userUpdateForm
    ) {
        UserDto userDto = userService.updateUser(email, userUpdateForm);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserDto> deleteUser(
            @PathVariable("email") String email
    ) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}