package com.sdv.FoodService.Repositories;

import com.sdv.FoodService.Models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food findByTitle(String title);

}
