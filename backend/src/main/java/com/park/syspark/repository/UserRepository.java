package com.park.syspark.repository;

import com.park.syspark.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String description);
    Optional<UserModel> findByEmailAndIsActiveTrue(String description);
    List<UserModel> findByIsActiveTrue();

}
