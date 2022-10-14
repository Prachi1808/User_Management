package com.bridgelabz.UserManagement.model;
import com.bridgelabz.UserManagement.dto.LoginDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int loginHistoryId;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "updatedTimeStamp")
    LoginDTO loginDTO;

}

