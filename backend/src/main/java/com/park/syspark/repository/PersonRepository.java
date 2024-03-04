package com.park.syspark.repository;

import com.park.syspark.model.PersonModel;
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
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {

    Optional<PersonModel> findByCpf(String cpf);

    Optional<PersonModel> findByCpfAndIsActiveTrue(String cpf);
    List<PersonModel> findByIsActiveTrue();

}


