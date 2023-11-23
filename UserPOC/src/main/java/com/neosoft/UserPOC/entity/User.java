package com.neosoft.UserPOC.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "pincode", nullable = false)
    private int pinCode;

    @Column(name = "joning_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;

    @Column(name = "mobile_number", nullable = false)
    private int mobileNum;

    @Column(name = "isActive", nullable = false)
    private boolean isActive = Boolean.TRUE;

}
