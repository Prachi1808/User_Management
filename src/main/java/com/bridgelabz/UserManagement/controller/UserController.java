package com.bridgelabz.UserManagement.controller;
import com.bridgelabz.UserManagement.dto.LoginDTO;
import com.bridgelabz.UserManagement.dto.ResponseDTO;
import com.bridgelabz.UserManagement.dto.UserDTO;
import com.bridgelabz.UserManagement.dto.UserPrivilegeDTO;
import com.bridgelabz.UserManagement.model.User;
import com.bridgelabz.UserManagement.model.UserPrivilege;
import com.bridgelabz.UserManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
    @RequestMapping("/user")
    public class UserController {
    @Autowired
    IUserService iuserService;

    @PostMapping("/insert_user")
    public ResponseEntity<ResponseDTO> AddUser(@RequestBody UserDTO userDTO) {
        String response = iuserService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Sucessfully added", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> getUserData(@PathVariable String token) {
        User userData = null;
        userData = iuserService.getUserDataById(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call For ID Successful", userData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     * Get Api for Forgot password with email
     */
    @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<ResponseDTO> forgotPasswordByemail(@PathVariable String email) {
        String response = iuserService.forgotPassword(email);
        ResponseDTO respDTO = new ResponseDTO("*** Link send successfully ***", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Post Api for resetPassword user data
     */
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody LoginDTO loginDto) {
        String response = iuserService.resetPassword(loginDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token) {
        User response = iuserService.verifyUser(token);
        ResponseDTO responseDTO = new ResponseDTO("User verified successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Post Api for Login for particular user
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        User response = iuserService.loginUser(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Login Successful!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseDTO> getAllUser() {
        List<User> newUser = iuserService.getAllOrderItems();
        ResponseDTO responseDTO = new ResponseDTO("All Users records retrieved successfully !", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable int id, @Valid @RequestBody UserDTO userDto) {
        String response = iuserService.editById(id, userDto);
        ResponseDTO responseDTO = new ResponseDTO("Updated UserManagement Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getGroupByUnder18")
    public ResponseEntity<ResponseDTO> getAllUsersByUnder18() {
        int newUser = iuserService.getGroupByUnder18();
        ResponseDTO responseDTO = new ResponseDTO("All Users Age records retrieved successfully !", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.OK);


    }

    @GetMapping("/getGroupByAge18to40")
    public ResponseEntity<ResponseDTO> getAllUsersByAge18to40() {
        int newUser = iuserService.getGroupByAge18to40();
        ResponseDTO responseDTO = new ResponseDTO("All Users Age records retrieved successfully !", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getGroupByAbove40")
    public ResponseEntity<ResponseDTO> getAllUsersByAbove40() {
        int newUser = iuserService.getGroupByAbove40();
        ResponseDTO responseDTO = new ResponseDTO("All Users Age records retrieved successfully !", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.OK);

    }
    @GetMapping("/get/{address}")
    public ResponseEntity<ResponseDTO> getUserByLocation(@PathVariable String address) {
        int newUser = iuserService.getAllUsersbyLocation(address);
        ResponseDTO responseDTO = new ResponseDTO("Data by using Location!", newUser);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/getgender/{gender}")
    public ResponseEntity<ResponseDTO> getUserByGender(@PathVariable String gender) {
        int newUser = iuserService.getAllUsersbyGender(gender);
        ResponseDTO responseDTO = new ResponseDTO("Particular gender percentage....", newUser+"%");
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping(value = "/recent-registration")
    public ResponseEntity<ResponseDTO> getLatestRegistration() {
        List<User> newUser =iuserService.getRecentRegistrationList();
        ResponseDTO responseDTO = new ResponseDTO("Recent Registration....", newUser);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping(value = "/all-registration")
    public ResponseEntity<ResponseDTO> getALLRegistration() {
        List<User> newUser =iuserService.getAllRegistrationList();
        ResponseDTO responseDTO = new ResponseDTO("All Registration....", newUser);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @PostMapping("/addpermission")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody UserPrivilegeDTO userPrivilegeDto) {
        UserPrivilege permission = iuserService.addpermission(userPrivilegeDto);
        ResponseDTO responseDTO = new ResponseDTO("cart details added", permission);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/LoginHistory")
    public ResponseEntity<ResponseDTO> LoginHistory(@Valid @RequestBody UserPrivilegeDTO userPrivilegeDto) {
        UserPrivilege permission = iuserService.loginHistory(userPrivilegeDto);
        ResponseDTO responseDTO = new ResponseDTO("cart details added", permission);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}