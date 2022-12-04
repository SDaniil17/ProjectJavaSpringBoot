package com.sdv.FoodService.Controllers.RestContollers;

import com.sdv.FoodService.Models.Order;
import com.sdv.FoodService.Models.Request.OrderRequestModel;
import com.sdv.FoodService.Models.Response.OrderResponseModel;
import com.sdv.FoodService.Models.User;
import com.sdv.FoodService.Security.Jwt.JwtTokenProvider;
import com.sdv.FoodService.Services.IFoodService;
import com.sdv.FoodService.Services.IOrderService;
import com.sdv.FoodService.Services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Get all user orders")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponseModel>> Orders(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.FindByUsername(username);

        List<OrderResponseModel> orders = orderService.GetByUserResponseModel(user);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @Operation(summary = "Add Order")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseModel> AddOrder(@RequestBody OrderRequestModel orderRequestModel, HttpServletRequest request){

        //needed to JSON validation

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.FindByUsername(username);

        Order order = orderService.Add(orderRequestModel,user);
        OrderResponseModel response = new OrderResponseModel(order);
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
