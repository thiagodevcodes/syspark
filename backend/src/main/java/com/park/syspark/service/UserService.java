package com.park.syspark.service;


import com.park.syspark.model.RoleModel;
import com.park.syspark.model.UserModel;
import com.park.syspark.repository.UserRepository;
import com.park.syspark.rest.dto.RoleDto;
import com.park.syspark.rest.dto.UserDto;
import com.park.syspark.rest.form.UserLoginForm;
import com.park.syspark.rest.form.UserUpdateForm;
import com.park.syspark.service.exceptions.user.UserInsertException;
import com.park.syspark.service.exceptions.user.UserNotFoundException;
import com.park.syspark.service.exceptions.user.UserUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public UserDto getUserDtoByDescription(String description) {
        UserModel userModel = findUserModelByDescription(description);
        return convertModelToDto(userModel);
    }

    public UserModel findUserModelByDescription(String description) {
        return userRepository.findByEmailAndIsActiveTrue(description)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("The user ‘%s’ was not found", description)
                ));
    }

    public List<UserDto> getAllUserDto() {
        List<UserModel> userModelList = userRepository.findByIsActiveTrue();
        if (userModelList.isEmpty()) {
            throw new UserNotFoundException("No active user was found");
        }
        return convertModelListToDtoList(userModelList);
    }

    @Transactional
    public UserDto insertUser(UserLoginForm userForm) {
        if (userRepository.findByEmail(userForm.getEmail()).isPresent()) {
            throw new UserInsertException(
                    String.format("The user ‘%s’ is already registered", userForm.getEmail())
            );
        }
        RoleModel roleModel = roleService.findRoleModelByDescription("Administrador");
        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleModel);
        try {
            UserModel userModel = convertFormToModel(userForm);
            Date date = new Date();
            userModel.setCreatedAt(date);
            userModel.setUpdatedAt(date);
            userModel.setIsActive(true);
            userModel.setVersion(1);
            userModel.setRoles(roles);
            userRepository.save(userModel);
            return convertModelToDto(userModel);
        } catch (DataIntegrityViolationException err) {
            throw new UserInsertException(String.format("Failed to register the user ‘%s’. Check if the data is correct", userForm.getEmail()));
        }
    }

    @Transactional
    public UserDto updateUser(String description, UserUpdateForm userUpdateForm) {
        try {
            UserModel userModel = findUserModelByDescription(description);
            userModel.setEmail(userUpdateForm.getEmail());
            userModel.setUpdatedAt(new Date());
            userRepository.save(userModel);
            return convertModelToDto(userModel);
        } catch (DataIntegrityViolationException err) {
            throw new UserUpdateException(String.format("Failed to update the user ‘%s’. Check if the data is correct", description));
        }
    }

    @Transactional
    public void deleteUser(String description) {
        try {
            UserModel userModel = findUserModelByDescription(description);
            userModel.setIsActive(false);
            userModel.setUpdatedAt(new Date());
            userRepository.save(userModel);
        } catch (DataIntegrityViolationException err) {
            throw new UserUpdateException(String.format("Failed to update the user ‘%s’. Check if the data is correct", description));
        }
    }

    private UserDto convertModelToDto(UserModel userModel) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userModel.getEmail());
        Set<RoleDto> roleDtos = userModel.getRoles().stream()
                .map(roleService::convertModelToDto)
                .collect(Collectors.toSet());
        userDto.setRoles(roleDtos);
        return userDto;
    }

    private List<UserDto> convertModelListToDtoList(List<UserModel> list) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (UserModel userModel : list) {
            UserDto userDto = convertModelToDto(userModel);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    private UserModel convertFormToModel(UserLoginForm userForm) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userForm.getEmail());
        userModel.setPassword(userForm.getPassword());
        return userModel;
    }
}
