package com.bridgelabz.UserManagement.service;
import com.bridgelabz.UserManagement.dto.LoginDTO;
import com.bridgelabz.UserManagement.dto.UserDTO;
import com.bridgelabz.UserManagement.dto.UserPrivilegeDTO;
import com.bridgelabz.UserManagement.model.User;
import com.bridgelabz.UserManagement.model.UserPrivilege;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);

    User verifyUser(String token);

    User getUserDataById(String token);

    String forgotPassword(String email);

    String resetPassword(LoginDTO loginDto);

    User loginUser(LoginDTO loginDTO);

    List<User> getAllOrderItems();


    String editById(int id, UserDTO userDto);

    int getGroupByUnder18();

    int getGroupByAge18to40();

    int getGroupByAbove40();

    int getAllUsersbyLocation(String address);

    int getAllUsersbyGender(String gender);

    List<User> getRecentRegistrationList();

    List<User> getAllRegistrationList();

    UserPrivilege addpermission(UserPrivilegeDTO userPrivilegeDto);

    UserPrivilege loginHistory(UserPrivilegeDTO userPrivilegeDto);
}

