package com.bridgelabz.UserManagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class LoginHistoryDTO {
    int id;
    LocalDateTime updatedTimeStamp = LocalDateTime.now();

}
