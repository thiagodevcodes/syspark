package com.park.syspark.service;

import com.park.syspark.model.RoleModel;
import com.park.syspark.repository.RoleRepository;
import com.park.syspark.rest.dto.RoleDto;
import com.park.syspark.rest.form.RoleForm;
import com.park.syspark.rest.form.RoleUpdateForm;
import com.park.syspark.service.exceptions.role.RoleInsertException;
import com.park.syspark.service.exceptions.role.RoleNotFoundException;
import com.park.syspark.service.exceptions.role.RoleUpdateException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto getRoleDtoByDescription(String description) {
        RoleModel roleModel = findRoleModelByDescription(description);
        return convertModelToDto(roleModel);
    }

    public RoleModel findRoleModelByDescription(String description) {
        return roleRepository.findByDescriptionAndIsActiveTrue(description)
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format("The user role ‘%s’ was not found", description)
                ));
    }

    public List<RoleDto> getAllRoleDto() {
        List<RoleModel> roleModelList = roleRepository.findByIsActiveTrue();
        if (roleModelList.isEmpty()) {
            throw new RoleNotFoundException("No active user role was found");
        }
        return convertModelListToDtoList(roleModelList);
    }

    @Transactional
    public RoleDto insertRole(RoleForm roleForm) {
        if (roleRepository.findByDescription(roleForm.getDescription()).isPresent()) {
            throw new RoleInsertException(
                    String.format("The user role ‘%s’ is already registered", roleForm.getDescription())
            );
        }
        try {
            RoleModel roleModel = convertFormToModel(roleForm);
            Date date = new Date();
            roleModel.setCreatedAt(date);
            roleModel.setUpdatedAt(date);
            roleModel.setIsActive(true);
            roleModel.setVersion(1);
            roleRepository.save(roleModel);
            return convertModelToDto(roleModel);
        } catch (DataIntegrityViolationException err) {
            throw new RoleInsertException(String.format("Failed to register the user role ‘%s’. Check if the data is correct", roleForm.getDescription()));
        }
    }

    @Transactional
    public RoleDto updateRole(String description, RoleUpdateForm roleUpdateForm) {
        try {
            RoleModel roleModel = findRoleModelByDescription(description);
            roleModel.setDescription(roleUpdateForm.getDescription());
            roleModel.setUpdatedAt(new Date());
            roleRepository.save(roleModel);
            return convertModelToDto(roleModel);
        } catch (DataIntegrityViolationException err) {
            throw new RoleUpdateException(String.format("Failed to update the user role ‘%s’. Check if the data is correct", description));
        }
    }

    @Transactional
    public void deleteRole(String description) {
        try {
            RoleModel roleModel = findRoleModelByDescription(description);
            roleModel.setIsActive(false);
            roleModel.setUpdatedAt(new Date());
            roleRepository.save(roleModel);
        } catch (DataIntegrityViolationException err) {
            throw new RoleUpdateException(String.format("Failed to update the user role ‘%s’. Check if the data is correct", description));
        }
    }

    public RoleDto convertModelToDto(RoleModel roleModel) {
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription(roleModel.getDescription());
        return roleDto;
    }

    public List<RoleDto> convertModelListToDtoList(List<RoleModel> list) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleModel roleModel : list) {
            RoleDto roleDto = convertModelToDto(roleModel);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }

    public RoleModel convertFormToModel(RoleForm roleForm) {
        RoleModel roleModel = new RoleModel();
        roleModel.setDescription(roleForm.getDescription());
        return roleModel;
    }
}
