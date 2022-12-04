package com.sdv.FoodService;

import com.sdv.FoodService.Services.IFoodService;
import com.sdv.FoodService.Services.IOrderService;
import com.sdv.FoodService.Services.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServicesTests {

	@Autowired
	private IUserService userService;

	@Autowired
	private IFoodService foodService;

	@Autowired
	private IOrderService orderService;

	@Test
	public void getUserByName() {
		Assert.assertNotNull(userService.FindByUsername("Trifanov"));
	}

	@Test
	public void getFoodById() {
		Assert.assertNotNull(foodService.FindById(57));
	}

	@Test
	public void getOrderById(){
		Assert.assertNotNull(orderService.GetById(72));
	}

	@Test
	public void getFoodByTitle() {
		Assert.assertNotNull(foodService.FindByName("Lays"));
	}
}
