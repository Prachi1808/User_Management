package com.bridgelabz.UserManagement.service;
import com.bridgelabz.UserManagement.dto.LoginDTO;
import com.bridgelabz.UserManagement.dto.UserDTO;
import com.bridgelabz.UserManagement.dto.UserPrivilegeDTO;
import com.bridgelabz.UserManagement.exceptions.UserException;
import com.bridgelabz.UserManagement.model.User;
import com.bridgelabz.UserManagement.model.UserPrivilege;
import com.bridgelabz.UserManagement.repository.UserPrivilegeRepo;
import com.bridgelabz.UserManagement.repository.UserRepository;
import com.bridgelabz.UserManagement.util.EmailSenderService;
import com.bridgelabz.UserManagement.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPrivilegeRepo userPrivilegeRepo;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        userRepository.save(user);
        String token = tokenUtil.createToken(user.getId());
        emailSenderService.sendEmail(user.getEmailId(), "Added Your Details", "http://localhost:8081/user/verify/" + token);
        return token;
    }

    @Override
    public User verifyUser(String token) {
        User user = this.getUserDataById(token);
        user.setVerified(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserDataById(String token) {
        int id = tokenUtil.decodeToken(token);
        return userRepository.findById(id).orElseThrow(() -> new UserException
                ("Employee id  " + id + " note Found "));
    }

    @Override
    public String forgotPassword(String email) {

        User editdata = userRepository.findByEmail(email);
        if (editdata != null) {
            emailSenderService.sendEmail(editdata.getEmailId(), "About Login", "http://localhost:5000/user/resetPassword/" + email);
            return "Reset link send sucessfully";
        } else
            throw new
                    UserException("Login Failed, Wrong email or password!!!");

    }

    @Override
    public String resetPassword(LoginDTO loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(loginDTO.getEmail()));
        String password = loginDTO.getPassword();
        if (userDetails.isPresent()) {
            userDetails.get().setPassword(password);
            userRepository.save(userDetails.get());
            return "Password Changed";
        } else
            return "Invalid Email Address";
    }
    @Override
    public User loginUser(LoginDTO loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(loginDTO.getEmail()));
        if (userDetails.isPresent()) {
            //String pass = login.get().getPassword();
            if (userDetails.get().getPassword().equals(loginDTO.getPassword())) {
                emailSenderService.sendEmail(userDetails.get().getEmailId(), "About Login", "Login Successful!");
                return userDetails.get();
            } else
                emailSenderService.sendEmail(userDetails.get().getEmailId(), "About Login", "Invalid password!");
            throw new UserException("Wrong Password!");
        } else
            throw new UserException("Login Failed, Wrong email or password!!!");
    }
    @Override
    public List<User> getAllOrderItems() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Override
    public String editById(int id, UserDTO userDto) {
        User editUser = userRepository.findById(id).orElse(null);
        if (editUser != null) {
            editUser.setFirstName(userDto.getFirstName());
            editUser.setMiddleName(userDto.getMiddleName());
            editUser.setLastName(userDto.getLastName());
            editUser.setAge(userDto.getAge());
            editUser.setGender(userDto.getGender());
            editUser.setContactNo(userDto.getContactNo());
            editUser.setEmailId(userDto.getEmailId());
            editUser.setPassword(userDto.getPassword());
            editUser.setAddress(userDto.getAddress());
            editUser.setRole(userDto.getRole());
            userRepository.save(editUser);
            String token = tokenUtil.createToken(editUser.getId());
            emailSenderService.sendEmail(editUser.getEmailId(), "Added Your Details", "http://localhost:5000/user/verify/" + token);
            return token;
        } else
            throw new UserException("Id:" + id + " is not present ");
    }

    @Override
    public int getGroupByUnder18() {
        List<User> users = userRepository.findByAgeUnder18();
        return users.size();

    }

    @Override
    public int getGroupByAge18to40() {
        List<User> users = userRepository.findByAge18to40();
        return users.size();

    }

    @Override
    public int getGroupByAbove40() {
        List<User> users = userRepository.findByAgeAbove40();
        return users.size();

    }
    @Override
    public int getAllUsersbyLocation( String address) {
        List<User> users = userRepository.findByAddress(address);
        return users.size();

    }
    @Override
    public int getAllUsersbyGender(String gender) {
        List<User> users = userRepository.findByGender(gender);
        int obtained= users.size();
        List<User>userList = getAllOrderItems();
        int total = userList.size();
        int  percentage= obtained * 100 / total;
        return percentage;
    }
    @Override
    public List<User> getRecentRegistrationList() {
        List<User> user =  userRepository.getRecentregistration();
        return user;
    }

    @Override
    public List<User> getAllRegistrationList() {
        List<User> user =  userRepository.getAllregistration();
        return user;
    }

    @Override
    public UserPrivilege addpermission(UserPrivilegeDTO userPrivilegeDto) {
        Optional<User> user = userRepository.findById(userPrivilegeDto.getId());
        if (user.isPresent()) {
            UserPrivilege details = new UserPrivilege(user.get(), userPrivilegeDto.isAddDashboard(), userPrivilegeDto.isDeleteDashboard(), userPrivilegeDto.isModifyDashboard(), userPrivilegeDto.isReadDashboard(),
                    userPrivilegeDto.isAddSettings(), userPrivilegeDto.isDeleteSettings(), userPrivilegeDto.isModifySettings(), userPrivilegeDto.isReadSettings(),
                    userPrivilegeDto.isAddUsersInformation(), userPrivilegeDto.isDeleteUsersInformation(), userPrivilegeDto.isModifyUsersInformation(), userPrivilegeDto.isReadUsersInformation(),
                    userPrivilegeDto.isAddWebPage1(), userPrivilegeDto.isDeleteWebPage1(), userPrivilegeDto.isModifyWebPage1(), userPrivilegeDto.isReadWebPage1(),
                    userPrivilegeDto.isAddWebPage2(), userPrivilegeDto.isDeleteWebPage2(), userPrivilegeDto.isModifyWebPage2(), userPrivilegeDto.isReadWebPage2(),
                    userPrivilegeDto.isAddWebPage3(), userPrivilegeDto.isDeleteWebPage3(), userPrivilegeDto.isModifyWebPage3(), userPrivilegeDto.isReadWebPage3());
            userPrivilegeRepo.save(details);
            return details;
        } else
            throw new UserException(" userid is invalid");
    }
    @Override
    public UserPrivilege loginHistory(UserPrivilegeDTO userPrivilegeDto) {
        return null;
    }

}
