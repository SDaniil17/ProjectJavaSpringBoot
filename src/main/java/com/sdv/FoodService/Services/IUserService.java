package com.sdv.FoodService.Services;

import com.sdv.FoodService.Models.User;

import java.util.List;

public interface IUserService {

    User Register(User user);

    List<User> getAll();

    User FindByUsername(String username);

    User FindById(Integer id);

    User FindByEmail(String email);

    User FindByPhone(String phone);

    void DeleteById(Integer id);
}
