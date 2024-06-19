package com.example.makersharksassessment.Repository;

import com.example.makersharksassessment.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,String> {
}
