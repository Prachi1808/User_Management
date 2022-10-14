package com.bridgelabz.UserManagement.repository;
import com.bridgelabz.UserManagement.model.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrivilegeRepo extends JpaRepository<UserPrivilege,Integer> {

}
