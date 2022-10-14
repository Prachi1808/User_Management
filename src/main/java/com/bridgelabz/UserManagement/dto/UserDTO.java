package com.bridgelabz.UserManagement.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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
}
