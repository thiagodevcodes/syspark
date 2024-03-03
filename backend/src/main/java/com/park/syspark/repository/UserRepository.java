package com.park.syspark.repository;

import com.park.syspark.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String email);

    @Query(value = "SELECT u.email AS user_email, r.description AS role_description " +
            "FROM TB_USER u " +
            "JOIN TB_USER_ROLE ur ON u.id = ur.id_user " +
            "JOIN TB_ROLE r ON ur.id_role = r.id " +
            "WHERE u.email = :userEmail", nativeQuery = true)
    List<Object[]> findUserRolesByEmail(@Param("userEmail") String userEmail);

    @Modifying
    @Query(value = "DELETE FROM TB_USER_ROLE ur WHERE ur.id_user = (SELECT u.id FROM TB_USER u WHERE u.email = :userEmail)", nativeQuery = true)
    void deleteUserRolesByEmail(@Param("userEmail") String userEmail);

    Optional<UserModel> findByEmailAndIsActiveTrue(String email);
    List<UserModel> findByIsActiveTrue();

}
