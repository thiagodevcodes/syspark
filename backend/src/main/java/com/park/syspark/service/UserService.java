package com.park.syspark.service;


import com.park.syspark.model.RoleModel;
import com.park.syspark.model.UserModel;
import com.park.syspark.repository.UserRepository;
import com.park.syspark.rest.dto.RoleDto;
import com.park.syspark.rest.dto.UserDto;
import com.park.syspark.rest.form.RoleForm;
import com.park.syspark.rest.form.UserLoginForm;
import com.park.syspark.rest.form.UserUpdateForm;
import com.park.syspark.service.exceptions.user.InvalidCredentials;
import com.park.syspark.service.exceptions.user.UserInsertException;
import com.park.syspark.service.exceptions.user.UserNotFoundException;
import com.park.syspark.service.exceptions.user.UserUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
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
        UserModel userModel = findUserModelByEmail(description);
        return convertModelToDto(userModel);
    }

    public UserModel findUserModelByEmail(String email) {
         UserModel userModel = userRepository.findByEmailAndIsActiveTrue(email)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("The user ‘%s’ was not found", email)
                ));
        List<Object[]> results = userRepository.findUserRolesByEmail(email);
        for (Object[] result : results) {
            String roleDescription = (String) result[1];
            RoleModel roleModel = new RoleModel();
            roleModel.setDescription(roleDescription);
            userModel.getRoles().add(roleModel);
        }
        return userModel;
    }

    public List<UserDto> getAllUserDto() {
        List<UserModel> userModelList = userRepository.findByIsActiveTrue();
        if (userModelList.isEmpty()) {
            throw new UserNotFoundException("No active user was found");
        }
        for (UserModel userModel : userModelList) {
            List<Object[]> results = userRepository.findUserRolesByEmail(userModel.getEmail());
            for (Object[] result : results) {
                String methodDescription = (String) result[1];
                RoleModel roleModel = new RoleModel();
                roleModel.setDescription(methodDescription);
                userModel.getRoles().add(roleModel);
            }
        }
        return convertModelListToDtoList(userModelList);
    }

    public UserDto login(UserLoginForm userLoginForm) {
        try {
            userRepository.findByEmail(userLoginForm.getEmail()).orElseThrow(
                    () -> new InvalidCredentials("Invalid login credentials")
            );
            UserModel userModel = findUserModelByEmail(userLoginForm.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!(passwordEncoder.matches(userLoginForm.getPassword(), userModel.getPassword()))) {
                throw new InvalidCredentials("Invalid login credentials");
            }
            return convertModelToDto(userModel);
        } catch (InvalidCredentials err) {
            throw new InvalidCredentials("Invalid login credentials");
        }
    }

    @Transactional
    public UserDto insertUser(UserLoginForm userForm) {
        if (userRepository.findByEmail(userForm.getEmail()).isPresent()) {
            throw new UserInsertException(
                    String.format("The user ‘%s’ is already registered", userForm.getEmail())
            );
        }
        RoleModel roleModel = roleService.findRoleModelByDescription("Administrator");
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
    public UserDto updateUser(String email, UserUpdateForm userUpdateForm) {
        try {
            UserModel userModel = findUserModelByEmail(email);
            userRepository.deleteUserRolesByEmail(email);
            userModel.getRoles().clear();
            userRepository.save(userModel);
            userModel.setEmail(userUpdateForm.getEmail());
            userModel.setUpdatedAt(new Date());
            Set<RoleModel> roleModels = new HashSet<>();
            for (RoleForm roleForm : userUpdateForm.getRoles()) {
                RoleModel roleModel = roleService.findRoleModelByDescription(roleForm.getDescription());
                roleModels.add(roleModel);
            }
            userModel.setRoles(roleModels);
            userRepository.save(userModel);
            return convertModelToDto(userModel);
        } catch (DataIntegrityViolationException err) {
            throw new UserUpdateException(String.format("Failed to update the user ‘%s’. Check if the data is correct", email));
        }
    }

    @Transactional
    public void deleteUser(String email) {
        try {
            UserModel userModel = findUserModelByEmail(email);
            userModel.getRoles().clear();
            userModel.setIsActive(false);
            userModel.setUpdatedAt(new Date());
            userRepository.save(userModel);
        } catch (DataIntegrityViolationException err) {
            throw new UserUpdateException(String.format("Failed to update the user ‘%s’. Check if the data is correct", email));
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
        userModel.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        return userModel;
    }
}
