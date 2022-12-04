package com.sdv.FoodService.Services;

import com.sdv.FoodService.Models.Order;
import com.sdv.FoodService.Models.User;
import com.sdv.FoodService.Models.Request.OrderRequestModel;
import com.sdv.FoodService.Models.Response.OrderResponseModel;

import java.util.List;

public interface IOrderService {

    Order Add(Order order);

    Order Add(OrderRequestModel requestOrder, User user);

    Order GetById(Integer id);

    List<Order> GetAll();

    List<Order> GetByUser(User user);

    List<OrderResponseModel> GetAllResponseModel();

    List<OrderResponseModel> GetByUserResponseModel(User user);

    Order SetDelivered(Order order);
}
