package com.park.syspark.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="TB_EMPLOYEE")
public class EmployeeModel extends PersonModel{

}
