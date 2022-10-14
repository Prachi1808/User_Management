package com.bridgelabz.UserManagement.model;
import com.bridgelabz.UserManagement.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String firstName;
    String middleName;
    String lastName;
    int age;
    String gender;
    String contactNo;
    String emailId;
    String password;
    String address;
    String role;
    LocalDateTime createdTimeStamp = LocalDateTime.now();
    LocalDateTime updatedTimeStamp = LocalDateTime.now();
    private boolean verified = false;
    public User(UserDTO userDTO){
        this.firstName=userDTO.getFirstName();
        this.middleName=userDTO.getMiddleName();
        this.lastName=userDTO.getLastName();
        this.age=userDTO.getAge();
        this.gender=userDTO.getGender();
        this.contactNo=userDTO.getContactNo();
        this.emailId=userDTO.getEmailId();
        this.password=userDTO.getPassword();
        this.address=userDTO.getAddress();
        this.role=userDTO.getRole();
        this.createdTimeStamp=userDTO.getCreatedTimeStamp();
        this.updatedTimeStamp=userDTO.getUpdatedTimeStamp();
    }


}

