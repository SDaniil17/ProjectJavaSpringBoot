package com.sdv.FoodService.Repositories;

import com.sdv.FoodService.Models.Order;
import com.sdv.FoodService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);

}
