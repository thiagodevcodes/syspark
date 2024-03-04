package com.park.syspark.repository;

import com.park.syspark.model.CustomerTypeModel;
import com.park.syspark.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerTypeModel, UUID> {

    Optional<RoleModel> findByName(String name);
    Optional<RoleModel> findByNameAndIsActiveTrue(String name);
    List<RoleModel> findByIsActiveTrue();

}
