package com.park.syspark.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="TB_CUSTOMER")
public class CustomerModel extends PersonModel{
    @OneToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private CustomerTypeModel customerType;
}
