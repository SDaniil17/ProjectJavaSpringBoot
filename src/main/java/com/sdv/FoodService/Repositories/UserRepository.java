package com.sdv.FoodService.Repositories;

import com.sdv.FoodService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String login);
    User findByEmail(String email);
    User findByPhone(String phone);
}
