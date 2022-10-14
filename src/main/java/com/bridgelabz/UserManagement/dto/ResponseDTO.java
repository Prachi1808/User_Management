package com.bridgelabz.UserManagement.dto;
import com.bridgelabz.UserManagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String message;
    private Object object;

    public ResponseDTO(String s, String response) {
        this.message = s;
        this.object = response;
    }
    public ResponseDTO(String s, User user) {
        this.message = s;
        this.object = user;
    }
}

